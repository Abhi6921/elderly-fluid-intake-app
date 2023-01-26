package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.ClipData.Item
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.*
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientViewModel : ViewModel() {

    var likedRecipeErrorMessage: String by mutableStateOf("")

    private val mutablefavoriteRecipe = MutableStateFlow<List<Recipe>?>(null)
    var favoriteRecipeState: StateFlow<List<Recipe>?> = mutablefavoriteRecipe

    var isLikedRecipesLoading = mutableStateOf(false)
    var norecipes = mutableStateOf(false)

    init {
        getAllLikedRecipes()
    }

    var logDrinkResponse: LogDrinkResponse by mutableStateOf(LogDrinkResponse())
    var logDrinkErrorMessage: String by mutableStateOf("")
    fun getCurrentFluidIntakeStatus(patientId: String) : LogDrinkResponse {
        viewModelScope.launch(Dispatchers.IO) {
            val usersAuthApi = UsersAuthApi.getUsersAuthApiInstance()
            val authToken = AppSession.getAuthToken()
            Log.d("Authtokenpatient", authToken)
            try {
                val currentStatus = usersAuthApi.getCurrentFluidStatus("Bearer ${authToken}", patientId)
                logDrinkResponse = currentStatus
            }
            catch (ex: Exception) {
                logDrinkErrorMessage = ex.message.toString()
            }
        }
        return logDrinkResponse
    }



    fun getAllLikedRecipes() {
        isLikedRecipesLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {

            val usersAuthApi = UsersAuthApi.getUsersAuthApiInstance()
            try {
                val authToken = AppSession.getAuthToken()
                val patientId = AppSession.getPatientId()

                val response = usersAuthApi.fetchAllLikedRecipes("Bearer ${authToken}", patientId)
                if (response.isSuccessful) {
                    mutablefavoriteRecipe.tryEmit(response.body())
                }
                else if (response.code() == 400) {
                    isLikedRecipesLoading.value = false
                    norecipes.value = true
                }

                isLikedRecipesLoading.value = false

            } catch (ex: Exception) {
                likedRecipeErrorMessage = ex.message.toString()
            }
        }
    }
    
    fun likeRecipeByPatient(
        patientId: String,
        recipeId: String,
        context: Context) {

        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance = UsersAuthApi.getUsersAuthApiInstance()
            val authToken = AppSession.getAuthToken()
            Log.d("likerecipe-patientId", patientId)
            Log.d("likerecipe-recipeId", recipeId)
            retrofitInstance.likeRecipeByPatient("Bearer $authToken", patientId, recipeId).enqueue(object :
                Callback<LikeRecipeResponse> {
                    override fun onFailure(call: Call<LikeRecipeResponse>, t: Throwable) {
                        Toast.makeText(context, "Something went wrong! ${t.message.toString()}", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<LikeRecipeResponse>,
                        response: Response<LikeRecipeResponse>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(context, "recipe added to favroites!", Toast.LENGTH_SHORT).show()
                            Log.d("recipe-LikeOnSuccess", "recipe liked successfully ${response.code().toString()}")
                        }
                        else {
                            Log.d("recipe-LikeOnFailure", "error! could not like the recipe ${response.code().toString()} ${response.message().toString()} ${response.headers()}")
                            Toast.makeText(context, "error, in adding the recipe to favorites", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }
    }

    fun removeLikeRecipeByPatient(patientId: String, recipeId: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance = UsersAuthApi.getUsersAuthApiInstance()
            val authToken = AppSession.getAuthToken()

            retrofitInstance.removeRecipeFromFavorites("Bearer ${authToken}", patientId, recipeId).enqueue(
                object : Callback<LikeRecipeResponse> {
                    override fun onFailure(call: Call<LikeRecipeResponse>, t: Throwable) {
                        Toast.makeText(context, "Something went wrong! ${t.message.toString()}", Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(
                        call: Call<LikeRecipeResponse>,
                        response: Response<LikeRecipeResponse>
                    ) {
                        if (response.isSuccessful || response.code() == 201) {
                            Toast.makeText(context, "recipe removed from favroites!", Toast.LENGTH_SHORT).show()
                            Log.d("LikeOnSuccess", "recipe removed successfully ${response.code().toString()}")
                        }
                        else {
                            Log.d("LikeOnFailure", "error! could not like the recipe ${response.code().toString()} ${response.message().toString()} ${response.headers()} ${response.errorBody().toString()} ${response.body()}")
                            Toast.makeText(context, "error, in removing the recipe from favorites", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }
    }

}
package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nl.narvekar.abhishek.omring_fluid_intake_app.api.RecipeAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkDate
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession

class RecipeViewModel : ViewModel() {
    var recipeListResponse: List<Recipe> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getRecipeList() {
        viewModelScope.launch(Dispatchers.IO) {

            val recipeAuthApi = RecipeAuthApi.getInstance()
            try {
                val recipeList = recipeAuthApi.getAllRecipes()
                Log.d(TAG, "getRecipeList api: ${recipeList.count()}")
                recipeListResponse = recipeList
            }catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    var recipeResponse: Recipe by mutableStateOf(Recipe())

     fun getRecipeById(recipeId: String) : Recipe {
        viewModelScope.launch(Dispatchers.IO) {
            val recipeAuthApi = RecipeAuthApi.getInstance()
            val recipe = recipeAuthApi.getRecipeById(recipeId)
            recipeResponse = recipe
        }
       return recipeResponse
    }

    var likedRecipeListResponse: List<Recipe> by mutableStateOf(listOf())
    var likedRecipeErrorMessage by mutableStateOf("")

    fun getAllLikedRecipes(patientViewModel: PatientViewModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val usersAuthApi = UsersAuthApi.getUsersAuthApiInstance()
            try {
                val authToken = AppSession.getAuthToken()
                val phoneNumber = AppSession.getPhoneNumber()
                val patient = patientViewModel.getPatientByPhoneNumber(phoneNumber)
                val likedRecipes = usersAuthApi.fetchAllLikedRecipes("Bearer ${authToken}", patient?.id.toString())
                likedRecipeListResponse = likedRecipes

            } catch (ex: Exception) {
                likedRecipeErrorMessage = ex.message.toString()
            }



        }
    }

}
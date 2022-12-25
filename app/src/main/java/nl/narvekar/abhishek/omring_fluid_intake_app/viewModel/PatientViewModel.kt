package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LogDrinkResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.data.PatientResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientViewModel : ViewModel() {
    var patientListResponse: ArrayList<PatientResponse> by mutableStateOf(arrayListOf())
    var errorMessage: String by mutableStateOf("")

    fun getAllPatients() {
        viewModelScope.launch(Dispatchers.IO) {
            val usersAuthApi = UsersAuthApi.getUsersAuthApiInstance()
            // admin token
            val authToken = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOlsiQURNSU4iLCJDQVJFX0dJVkVSIl0sImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiIrMzE2NDU4MjYwMDAiLCJuYmYiOjE2NzE2NTgyNTEsImV4cCI6MTcwMzE5NDI1MSwiaWF0IjoxNjcxNjU4MjUxLCJpc3MiOiJEcmlua0FwcFJlY2lwZXMuYXp1cmV3ZWJzaXRlcy5uZXQiLCJhdWQiOiJEcmlua0FwcFVzZXJzIC8gUGF0aWVudHMgLyBDYXJlZ2l2ZXJzIC8gQWRtaW5zIn0.sgh_qAXL9GyQ_GLiXjPOBxZBQlaSaC91Cxc8iobF9XM"

            Log.d("userAuthToken", authToken)
            try {
                val patientList = usersAuthApi.getAllPatients("Bearer ${authToken}")
                //Log.d("Patient List", "${patientList.count()}")
                patientListResponse = patientList
            } catch (ex: Exception) {
                errorMessage = ex.message.toString()
            }
        }
    }

    var logDrinkResponse: LogDrinkResponse by mutableStateOf(LogDrinkResponse())


    fun getCurrentFluidIntakeStatus(patientId: String) : LogDrinkResponse {
        viewModelScope.launch(Dispatchers.IO) {
            val usersAuthApi = UsersAuthApi.getUsersAuthApiInstance()
            val authToken = AppSession.getAuthToken()
            Log.d("Authtokenpatient", authToken)
            try {
                val currentStatus = usersAuthApi.getCurrentFluidStatus("Bearer ${authToken}", patientId)
                logDrinkResponse = currentStatus
                Log.d("achieved at api", "${currentStatus.Achieved}")
            }
            catch (ex: Exception) {
                errorMessage = ex.message.toString()
            }
        }
        return logDrinkResponse
    }

    var errorMessageForLikeRecipe: String by mutableStateOf("")
    fun likeRecipeByPatient(patientId: String, recipeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val usersAuthApi = UsersAuthApi.getUsersAuthApiInstance()
            val authToken = AppSession.getAuthToken()
            try {
                val response = usersAuthApi.likeRecipeByPatient("Bearer ${authToken}", patientId, recipeId)
                if (response.isSuccessful) {
                    Log.d("LikeOnSuccess", "recipe liked successfully ${response.code().toString()}")
                }
                else {
                    Log.d("LikeOnFailure", "error! could not like the recipe ${response.code().toString()}")
                }
            }catch (ex: Exception) {
                errorMessageForLikeRecipe = ex.message.toString()
            }
        }
    }

    fun getPatientByPhoneNumber(phoneNumber: String) : PatientResponse? {
        return patientListResponse.find { patientResponse ->
            patientResponse.phoneNumber == phoneNumber
        }
    }

}
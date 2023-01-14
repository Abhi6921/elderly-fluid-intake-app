package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.AUTH_TOKEN_KEY
import nl.narvekar.abhishek.omring_fluid_intake_app.api.DrinkAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Login
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LoginResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    val showLoginFailureDialog = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
     fun loginUser(
        context: Context,
        login: Login,
        navController: NavController
    ) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance = DrinkAuthApi.getInstance()
            retrofitInstance.loginUser(login).enqueue(
                object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {

                            val authToken = response.body()?.accessToken
                            val patientId = response.body()?.id
                            val firstname = response.body()?.firstName
                            val lastname = response.body()?.lastName
                            val dailyLimit = response.body()?.dailyLimit
                            val dailyGoal = response.body()?.dailyGoal
                            if (authToken != null) {
                                saveUserData(
                                    login.phoneNumber,
                                    login.password,
                                    authToken,
                                    patientId.toString(), firstname.toString(),
                                    lastname.toString(), dailyLimit!!, dailyGoal!!
                                )
                            }

                            Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                            navController.navigate(Routes.Home.route) {
                                popUpTo(Routes.Login.route) {
                                    inclusive = true
                                }
                            }
                            isLoading.value = false
                        }
                        else {
                            isLoading.value = false
                            showLoginFailureDialog.value = true
                        }
                    }
                }
            )
        }
    }

    fun logout(navController: NavController) {
        AppSession.removeUserData()
        navController.navigate(Routes.Start.route) {
            popUpTo(Routes.Login.route) {
                inclusive = true
            }
        }
    }

    private fun saveUserData(username: String, password: String, authToken: String, patientId: String, firstName: String, lastName: String, dailyLimit: Int, dailyGoal: Int) {
        AppSession.saveUserData(username, password, authToken, patientId, firstName, lastName, dailyLimit, dailyGoal)
    }
}
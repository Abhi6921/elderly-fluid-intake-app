package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.Constants.AUTH_TOKEN_KEY
import nl.narvekar.abhishek.omring_fluid_intake_app.api.DrinkAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Login
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LoginResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import okhttp3.Route
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    fun loginUser(
        context: Context,
        login: Login,
        navController: NavController,
        sharedPreferences: SharedPreferences
    ) {

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
                    if (response.code() == 200) {
                        val authToken = response.body()?.accessToken

                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString(AUTH_TOKEN_KEY, authToken).toString()
                        editor.apply()

                        Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()

                        navController.navigate(Routes.Home.route) {
                            popUpTo(Routes.Login.route) {
                                inclusive = true
                            }
                        }
                    }
                    else {
                        Toast.makeText(context, "Login failure: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }

    fun logout(navController: NavController, sharedPreferences: SharedPreferences) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(AUTH_TOKEN_KEY, "").toString()
        editor.clear()
        editor.apply()

        navController.navigate(Routes.Start.route) {
            popUpTo(Routes.Login.route) {
                inclusive = true
            }
        }
    }
}
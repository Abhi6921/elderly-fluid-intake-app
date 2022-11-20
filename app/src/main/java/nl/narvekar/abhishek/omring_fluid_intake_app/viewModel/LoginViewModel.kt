package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.api.DrinkAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Login
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LoginResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    fun loginUser(
        context: Context,
        login: Login,
        navController: NavController) {

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
}
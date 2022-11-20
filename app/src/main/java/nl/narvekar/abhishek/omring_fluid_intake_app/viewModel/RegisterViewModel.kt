package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Login
import nl.narvekar.abhishek.omring_fluid_intake_app.data.User
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    fun signUpUser(
        context: Context,
        user: User
    ) {
        val retrofitInstance = UsersAuthApi.getInstance()
        retrofitInstance.registerUser(user).enqueue(
            object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.code() == 201) {
                        Toast.makeText(context, "Register Successful!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(context, "register failure!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }
}
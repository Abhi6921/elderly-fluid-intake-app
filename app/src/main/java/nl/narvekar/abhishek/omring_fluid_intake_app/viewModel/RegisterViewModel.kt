package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.*
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class RegisterViewModel : ViewModel() {

    fun signUpUser(
        context: Context,
        user: UserRequest
    ) {
        val retrofitInstance = UsersAuthApi.getInstance()
            retrofitInstance.registerUser(user).enqueue(
                object : Callback<UserResponse> {
                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        Toast.makeText(context, "Error registering the user: " + t.message.toString(), Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                        if (response.code() == 201) {
                            Toast.makeText(context, "User registered successfully!", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(context, "register failure! ${response.code().toString()}", Toast.LENGTH_SHORT).show()
                            Log.d("ERROR Description: ", response.body().toString())
                            Log.d("Error Description: ", "${response.errorBody().toString()}")
                        }

                    }
                }
            )

    }
}
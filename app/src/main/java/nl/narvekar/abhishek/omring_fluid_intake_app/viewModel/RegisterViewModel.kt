package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.*
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class RegisterViewModel : ViewModel() {

    val showSuccessMessage =  mutableStateOf(false)
    val showFailureMessage = mutableStateOf(false)
    var isProcessing = mutableStateOf(false)

    fun registerUser(
        context: Context,
        user: UserRequest
    ) {
        isProcessing.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance = UsersAuthApi.getUsersAuthApiInstance()
            retrofitInstance.registerUser(user).enqueue(object :
                Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Toast.makeText(context, "Something went wrong!", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.isSuccessful || response.code() == 201) {
                        isProcessing.value = false
                        showSuccessMessage.value = true
                    }
                    else {
                        isProcessing.value = false
                        showFailureMessage.value = true
                        Log.d("Register Failure!", "${response.code().toString()},  ${response.message()} ${response.headers()}")
                    }
                }
            }
            )

        }
    }
}
package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.AUTH_TOKEN_KEY
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LogDrink
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LogDrinkResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.data.UserResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogDrinkViewModel : ViewModel() {

    fun postANewDrink(
        context: Context,
        logdrink: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val authToken = AppSession.getAuthToken()
            Log.d("USERauthToken", authToken)
            val retrofitInstance = UsersAuthApi.getUsersAuthApiInstance()
            retrofitInstance.postNewDrink("Bearer ${authToken}", logdrink).enqueue(
                object : Callback<LogDrinkResponse> {

                    override fun onFailure(call: Call<LogDrinkResponse>, t: Throwable) {
                        Toast.makeText(context, "Something Went Wrong: ${t.message.toString()}", Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(
                        call: Call<LogDrinkResponse>,
                        response: Response<LogDrinkResponse>
                    ) {
                        val intakeNow = response.body()?.DrankNow
                        if (intakeNow != null) {
                            saveTodaysIntake(intakeNow.toFloat())
                        }
                        Toast.makeText(context, "Drink logged in succesfully!", Toast.LENGTH_LONG).show()
                    }
                }
            )
        }
    }

    fun saveTodaysIntake(drinkAmount: Float) {
        AppSession.saveTodaysIntake(drinkAmount)
    }
}
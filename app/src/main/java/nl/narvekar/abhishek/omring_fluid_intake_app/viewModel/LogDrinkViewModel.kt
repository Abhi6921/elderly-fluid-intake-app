package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.nfc.Tag
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.Constants.AUTH_TOKEN_KEY
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LogDrink
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LogDrinkResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogDrinkViewModel : ViewModel() {

    val drankNow = mutableStateOf("")
    val dailyLimit = mutableStateOf("")
    val amountLeftToLimit = mutableStateOf("")


    fun postANewDrink(
        context: Context,
        logdrink: LogDrink,
        sharedPreferences: SharedPreferences
    ) {
        val authToken = sharedPreferences.getString(AUTH_TOKEN_KEY, "").toString()

        val retrofitInstance = UsersAuthApi.getInstance()
        retrofitInstance.postNewDrink(authToken, logdrink).enqueue(
            object : Callback<LogDrinkResponse> {

                override fun onFailure(call: Call<LogDrinkResponse>, t: Throwable) {
                    Toast.makeText(context, "Something Went Wrong: ${t.message.toString()}", Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<LogDrinkResponse>,
                    response: Response<LogDrinkResponse>
                ) {
                    drankNow.value = response.body()?.drankNow.toString()
                    dailyLimit.value = response.body()?.dailyLimit.toString()
                    amountLeftToLimit.value = response.body()?.amountLeftToLimit.toString()
                }
            }
        )
    }
}
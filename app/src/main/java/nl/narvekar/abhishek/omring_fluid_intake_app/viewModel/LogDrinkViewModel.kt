package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.narvekar.abhishek.omring_fluid_intake_app.Constants.AUTH_TOKEN_KEY
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LogDrink
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LogDrinkResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.data.UserResponse
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
        sharedPreferences: SharedPreferences,
        setValue: (Float) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val authToken = sharedPreferences.getString(AUTH_TOKEN_KEY, "").toString()

            val retrofitInstance = UsersAuthApi.getInstance()
            retrofitInstance.postNewDrink(authToken, logdrink).enqueue(
                object : Callback<LogDrinkResponse> {

                    override fun onFailure(call: Call<LogDrinkResponse>, t: Throwable) {
                        Toast.makeText(context, "Something Went Wrong: ${t.message.toString()}", Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(
                        call: Call<LogDrinkResponse>,
                        response: Response<LogDrinkResponse>
                    ) {
                        //Toast.makeText(context, authToken, Toast.LENGTH_LONG).show()

                        Toast.makeText(context, response.code().toString(), Toast.LENGTH_LONG).show()
                        //Toast.makeText(context, response.message().toString(), Toast.LENGTH_LONG).show()

                        //Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()
                        drankNow.value = response.body()?.drankNow.toString()
                        dailyLimit.value = response.body()?.dailyLimit.toString()
                        amountLeftToLimit.value = response.body()?.amountLeftToLimit.toString()

                        //val a = (drankNow.value.toInt() / amountLeftToLimit.value.toInt()) * 100

                        //Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show()
                        //Toast.makeText(context, amountLeftToLimit.value.toString(), Toast.LENGTH_SHORT).show()
//                        val floatAmount = (drankNow.value.toFloat() / ((20f) / 100f))
//                        Toast.makeText(context, "$floatAmount", Toast.LENGTH_SHORT).show()
                        //setValue(a.toFloat())
                    }
                }
            )
        }
    }

    var patientResponse: UserResponse by mutableStateOf(UserResponse())
    var errorMessage: String by mutableStateOf("")

    fun getPatientById(authToken: String, id: String) : UserResponse {
        viewModelScope.launch(Dispatchers.Default) {
            val apiSerivce = UsersAuthApi.getInstance()

            try {
                val patient = apiSerivce.getPatientById(authToken, id)
                patientResponse = patient
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d(TAG, errorMessage.toString())
            }
        }
        return patientResponse
    }
}
package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LogDrinkResponse
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
                        if(response.isSuccessful || response.code() == 201) {
                            Toast.makeText(context, "Drink logged in succesfully!", Toast.LENGTH_LONG).show()
                        }
                        else {
                            Toast.makeText(context, "Error Logging in the drinks!", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            )
        }
    }


}
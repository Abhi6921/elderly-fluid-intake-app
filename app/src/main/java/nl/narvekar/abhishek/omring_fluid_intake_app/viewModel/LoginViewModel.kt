package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.narvekar.abhishek.omring_fluid_intake_app.api.DrinkApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Login

class LoginViewModel : ViewModel() {

    private val loginRequestLiveData = MutableLiveData<Boolean>()

    fun login(phonenumber: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val authService = DrinkApi.getInstance()
                val responseService = authService.login(Login(phonenumber, password))

                if (responseService.isSuccessful) {
                    responseService.body()?.let { tokenDto ->
                        Log.d("Logging", "Response token: ${tokenDto.accessToken}")
                    }
                }
                else {
                    responseService.errorBody().let { error ->
                        // start here from tomorrow
                    }
                }
            }catch (e: Exception) {
                Log.d("LOgging", "Error Authentication", e)
            }
        }
    }
}
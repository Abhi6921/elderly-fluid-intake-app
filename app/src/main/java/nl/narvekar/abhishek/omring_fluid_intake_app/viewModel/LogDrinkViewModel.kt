package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.nfc.Tag
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LogDrinkResponse

class LogDrinkViewModel : ViewModel() {

    fun logNewDrink(authToken: String, amount: Int) {
    }
}
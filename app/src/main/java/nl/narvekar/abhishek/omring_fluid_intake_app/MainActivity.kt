package nl.narvekar.abhishek.omring_fluid_intake_app

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nl.narvekar.abhishek.omring_fluid_intake_app.data.PatientResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.AUTH_TOKEN_KEY

import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppNavigation
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.ElderlyfluidintakeappTheme
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.SetCircularProgress
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.*

class MainActivity : ComponentActivity() {

//    private val recipeViewModel by viewModels<RecipeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppSession.startSession(this)
            ElderlyfluidintakeappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   AppNavigation()
                }
            }
        }
    }
}

//2345678982


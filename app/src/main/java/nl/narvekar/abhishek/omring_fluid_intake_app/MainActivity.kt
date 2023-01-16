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


    // TODO: paginate the drink records page
    // TODO: fix annotations requiring a certain api level
    // TODO: schedule notifications from the app

//    The end-user should be able to log in to the application and create a new account/profile -> done
//
//    The end-user should be able to adjust their daily goal of fluid intake. DONE
//
//    Adjust the times during which they would like to receive notifications from the application
//
//    View the notification message on the screen
//
//    Enter the amount of fluid they drank into the app DONE
//
//    See their fluid intake overview for the day DONE
//
//    Check if they have met their goal of drinking fluids DONE
//
//    Able to check out new recipes of drinks that make drinking fluids more fun DONE
//
//    When half of the daily goal is achieved a complementary message will be given through the application. DONE

    private val recipeViewModel by viewModels<RecipeViewModel>()
    private val cardListViewModel by viewModels<CardListViewModel>()

    // username: +31612345678 password: Mona12345! ROLE: CAREGIVER, ADMIN
    // username: +31246846878 password: Mona12345! ROLE: CAREGIVER
    // patient in db-> username: +3165874123651 password: Mona12345!
    // patient in db-> username: +3113579123579 password: Mona12345!, max


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
                   AppNavigation(recipeViewModel, cardListViewModel)
                }
            }
        }
    }
}


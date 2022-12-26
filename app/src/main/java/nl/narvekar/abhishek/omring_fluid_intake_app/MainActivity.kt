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
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.PrefKey
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppNavigation
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.ElderlyfluidintakeappTheme
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.*

class MainActivity : ComponentActivity() {
    // this commit is from the fetch-drink-records-branch

    private val loginViewModel by viewModels<LoginViewModel>()
    private val registerViewModel by viewModels<RegisterViewModel>()
    private val recipeViewModel by viewModels<RecipeViewModel>()
    private val expandableListViewModel by viewModels<CardListViewModel>()
    private val logDrinkViewModel by viewModels<LogDrinkViewModel>()
    private val patientViewModel by viewModels<PatientViewModel>()

    // username: +31612345678 password: Mona12345! ROLE: CAREGIVER, ADMIN
    // username: +31246846878 password: Mona12345! ROLE: CAREGIVER
    // patient in db-> username: +3165874123651 password: Mona12345!
    // patient in db-> username: +3113579123579 password: Mona12345!, max

    @RequiresApi(Build.VERSION_CODES.O)
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
                   //RecipeList(recipes = recipeViewModel.recipeListResponse)


                   AppNavigation(
                       loginViewModel = loginViewModel,
                       registerViewModel,
                       recipeViewModel,
                       expandableListViewModel,
                       logDrinkViewModel,
                       patientViewModel
                   )
                    recipeViewModel.getRecipeList()
                    patientViewModel.getAllPatients()
                    recipeViewModel.getAllLikedRecipes(patientViewModel)
                    expandableListViewModel.getAllDrinkDates(patientViewModel)

                   Log.d("Patients", "${patientViewModel.patientListResponse.count()}")

                }

                // todo retrieve liked patients recipes -> DONE
                // todo fix the drink records expandable lists
                // todo fix the navigation onselected color
                // todo fix the register endpoint

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ElderlyfluidintakeappTheme {
        Greeting("Android")
    }
}


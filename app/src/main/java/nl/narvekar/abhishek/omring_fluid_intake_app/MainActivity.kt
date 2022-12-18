package nl.narvekar.abhishek.omring_fluid_intake_app

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
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
import nl.narvekar.abhishek.omring_fluid_intake_app.Constants.AUTH_TOKEN_KEY
import nl.narvekar.abhishek.omring_fluid_intake_app.Constants.PrefKey
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppNavigation
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.ElderlyfluidintakeappTheme
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.*

class MainActivity : ComponentActivity() {
    // this commit is from the records page branch
    lateinit var sharedPreferences: SharedPreferences
    private val loginViewModel by viewModels<LoginViewModel>()
    private val registerViewModel by viewModels<RegisterViewModel>()
    private val recipeViewModel by viewModels<RecipeViewModel>()
    private val expandableListViewModel by viewModels<CardListViewModel>()
    private val logDrinkViewModel by viewModels<LogDrinkViewModel>()

    // username: +31612345678 password: Mona12345! ROLE: CAREGIVER, ADMIN
    // username: +31246846878 password: Mona12345! ROLE: CAREGIVER

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ElderlyfluidintakeappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   //RecipeList(recipes = recipeViewModel.recipeListResponse)
                    sharedPreferences = getSharedPreferences(PrefKey, Context.MODE_PRIVATE)
                    val authToken = sharedPreferences.getString(AUTH_TOKEN_KEY, "").toString()

                   AppNavigation(
                       loginViewModel = loginViewModel,
                       registerViewModel,
                       recipeViewModel,
                       expandableListViewModel,
                       sharedPreferences,
                       authToken,
                       logDrinkViewModel
                   )
                    recipeViewModel.getRecipeList()
                }
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


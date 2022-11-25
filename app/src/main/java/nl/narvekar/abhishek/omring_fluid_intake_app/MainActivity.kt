package nl.narvekar.abhishek.omring_fluid_intake_app

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
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppNavigation
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.ElderlyfluidintakeappTheme
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.CardListViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LoginViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RecipeViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RegisterViewModel

class MainActivity : ComponentActivity() {

    val loginViewModel by viewModels<LoginViewModel>()
    val registerViewModel by viewModels<RegisterViewModel>()
    val recipeViewModel by viewModels<RecipeViewModel>()
    val expandableListViewModel by viewModels<CardListViewModel>()

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
                   AppNavigation(
                       loginViewModel = loginViewModel,
                       registerViewModel,
                       recipeViewModel,
                       expandableListViewModel
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


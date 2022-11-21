package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components.RecipeItem


@Composable
fun RecipeList(recipes: List<Recipe>, navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBarRecipes(navController)
        },
        content = { innerPadding ->
            LazyColumn(Modifier.padding(innerPadding)) {
                itemsIndexed(items = recipes) { index, item ->
                    RecipeItem(item)
                }
            }
        },
        bottomBar = {
            AppBottomNav()
        }
    )



}

@Composable
fun TopAppBarRecipes(navController: NavController) {
    TopAppBar(
        elevation = 4.dp,
        title = {
            Text("Recipes", color = Color.White)
        },
        backgroundColor =  Color(0xFF1BAEEE),
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Recipes.route) {
                        inclusive = true
                    }
                }
            }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        }, actions = {
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(Icons.Filled.Share, null)
            }
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(Icons.Filled.Settings, null)
            }
        }
    )
}
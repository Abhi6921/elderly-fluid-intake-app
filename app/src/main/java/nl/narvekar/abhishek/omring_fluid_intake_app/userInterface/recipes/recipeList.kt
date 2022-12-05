package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components.RecipeItem


@Composable
fun RecipeList(recipes: List<Recipe>, navController: NavController) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF1BAEEE),
                elevation = 0.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProvideTextStyle(value = MaterialTheme.typography.h6) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides ContentAlpha.high
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                text = "Recipes",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        },
        content = { innerPadding ->
            if (recipes.isEmpty()) {
                Text(text = "Recipes is empty")
            }
            else {
                LazyColumn(Modifier.padding(innerPadding)) {
                    items(recipes) {item ->
                        RecipeItem(item) {
                            navController.navigate(Routes.RecipeDetail.route + "/${it.recipeId}")
                        }
                    }
                }
            }

        },
        bottomBar = {
            AppBottomNav(navController)
        }
    )
}
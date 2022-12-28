package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components.RecipeItem
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.PatientViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RecipeViewModel

@Composable
fun RecipeList(recipes: List<Recipe>, navController: NavController, patientViewModel: PatientViewModel, recipeViewModel: RecipeViewModel) {

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth().height(60.dp),
                backgroundColor = Color(0xFF1BAEEE),
                elevation = 0.dp
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
                                color = Color.White,
                                fontSize = 34.sp
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
                    items(recipes) { item ->
                        RecipeItem(item, patientViewModel) {
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
package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components.RecipeItem
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RecipeViewModel

@Composable
fun RecipeList(
    navController: NavController,
    recipeViewModel: RecipeViewModel = viewModel()
) {
    val recipes by recipeViewModel.recipeListState.collectAsState()
    val isRecipesLoading = recipeViewModel.isLoading.value
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 4.dp,
                title = {
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
                                    text = stringResource(id = R.string.recipe_title),
                                    color = Color.White,
                                    fontSize = 34.sp
                                )
                            }
                        }
                    }
                },
                backgroundColor =  Color(0xFF1BAEEE)
            )
        },
        content = { innerPadding ->
            if (recipes?.isEmpty() == true) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.empty_recipe_text))
                }
            }
            if (isRecipesLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = Color.Blue, modifier = Modifier.size(70.dp))
                }
            }
            else {
                LazyColumn(Modifier.padding(innerPadding)) {
                     recipes?.let { allRecipes ->
                        items(allRecipes) { item ->
                            RecipeItem(item) {
                                navController.navigate(Routes.RecipeDetail.route + "/${it.recipeId}")
                            }
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
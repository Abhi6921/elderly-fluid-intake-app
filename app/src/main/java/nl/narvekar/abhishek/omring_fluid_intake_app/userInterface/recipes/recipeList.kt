package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components.RecipeItem


@Composable
fun RecipeList(recipes: List<Recipe>) {
    LazyColumn {
        itemsIndexed(items = recipes) { index, item ->
            RecipeItem(item)
        }
    }
}
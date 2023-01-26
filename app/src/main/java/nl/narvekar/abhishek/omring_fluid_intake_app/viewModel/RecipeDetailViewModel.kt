package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.provider.CalendarContract.CalendarEntity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nl.narvekar.abhishek.omring_fluid_intake_app.api.RecipeAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe

class RecipeDetailViewModel : ViewModel() {

    private val mutableRecipe = MutableStateFlow<Recipe?>(null)
    var mutableRecipeState: StateFlow<Recipe?> = mutableRecipe

    var errorMessage: String by mutableStateOf("")

    fun getRecipeById(recipeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val recipeAuthApi = RecipeAuthApi.getInstance()
                val recipe = recipeAuthApi.getRecipeById(recipeId)
                mapRecipe(recipe)
                mutableRecipe.emit(recipe)
            } catch (ex: Exception) {
                errorMessage = ex.message.toString()
            }
        }
    }
}

fun mapRecipe(entity: Recipe) : Result<Recipe> = runCatching {
    with(entity) {
        Recipe(
            recipeId,
            name,
            ingredients,
            instructions,
            visible,
            imageLink
        )
    }
}
package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.R
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

class RecipeViewModel : ViewModel() {
    //var recipeListResponse: List<Recipe> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    private val mutableListRecipe = MutableStateFlow<List<Recipe>?>(null)
    var recipeListState: StateFlow<List<Recipe>?> = mutableListRecipe

    var isLoading = mutableStateOf(false)

    init {
        getRecipeList()
    }

    fun getRecipeList() {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val recipeAuthApi = RecipeAuthApi.getInstance()
            try {
                val recipeList = recipeAuthApi.getAllRecipes()
                //recipeListResponse = recipeList
                mutableListRecipe.emit(recipeList)
                isLoading.value = false
            }catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}
package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RecipeViewModel
import kotlin.math.log


@Composable
fun RecipeDetailView(
    recipeViewModel: RecipeViewModel,
    detailId: String,
    navController: NavController
) {
    Log.d(TAG, "RecipeDetailView passed from recipe list: $detailId")
    val recipe = recipeViewModel.recipeListResponse.find { recipe ->
        detailId == recipe.recipeId
    }

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 4.dp,
                title = {
                    Text("I'm a TopAppBar")
                },
                backgroundColor =  MaterialTheme.colors.primarySurface,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }, actions = {
                    IconButton(onClick = {/* Do Something*/ }) {
                        Icon(Icons.Filled.Share, null)
                    }
                    IconButton(onClick = {/* Do Something*/ }) {
                        Icon(Icons.Filled.Settings, null)
                    }
                })
        },
        content = {
            Column {
                if (recipe != null) {
                    Text(text = recipe.name)
                    Divider(modifier = Modifier.padding(bottom = 34.dp))
                    for((key, value) in recipe.ingredients) {
                        Text(text = "$key: $value")
                    }
                }
            }

        }
    )
}


@Composable
fun RecipeImage() {
    AsyncImage(
        model = "",
        modifier = Modifier
            //.aspectRatio(image.intrinsicSize.width / image.intrinsicSize.height)
            .fillMaxWidth()
            .fillMaxHeight(),
        contentScale = ContentScale.Crop,
        contentDescription = "recipe image",
        placeholder = painterResource(R.drawable.placeholder)
    )
}
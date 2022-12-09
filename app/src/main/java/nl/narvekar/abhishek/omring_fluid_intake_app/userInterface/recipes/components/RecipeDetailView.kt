package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
    val scrollState = rememberScrollState()
    val recipe = recipeViewModel.recipeListResponse.find { recipe ->
        detailId == recipe.recipeId
    }

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 4.dp,
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        text = recipe?.name ?: "",
                        color = Color.White,
                        fontSize = 34.sp
                    )
                    if (recipe!= null) {
                        Text(text = recipe.name)
                    }
                },
                backgroundColor =  MaterialTheme.colors.primarySurface,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                })

        },
        content = {
            Column(Modifier.verticalScroll(scrollState)) {
                //RecipeImage()
                if (recipe != null) {
                    AsyncImage(
                        model = R.drawable.omring_logo,
                        contentDescription = "recipe image",
                        modifier = Modifier
                            .width(900.dp)
                            .height(800.dp),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(R.drawable.placeholder)
                    )
                    Text(text = recipe.name, fontSize = 44.sp)
                    Divider(modifier = Modifier.padding(bottom = 34.dp))
                    Text(text = "Ingredients:", fontSize = 34.sp)
                    Divider(modifier = Modifier.padding(34.dp))
                    for((key, value) in recipe.ingredients) {
                        Text(text = "$key: $value", fontSize = 24.sp)
                    }
                    Divider(modifier = Modifier.padding(34.dp))
                    Text(text = "Steps", fontSize = 34.sp)
                    Divider(modifier = Modifier.padding(34.dp))
                    Text(text = recipe.instructions, fontSize = 34.sp)
                }
            }

        }
    )
}
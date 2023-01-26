package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import nl.narvekar.abhishek.omring_fluid_intake_app.data.placeholderText
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.PatientViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RecipeDetailViewModel


@Composable
fun RecipeDetailView(
    recipeDetailViewModel: RecipeDetailViewModel = viewModel(),
    detailId: String,
    patientViewModel: PatientViewModel = viewModel(),
    navController: NavController
) {
    val scrollState = rememberScrollState()
    val patientId = AppSession.getPatientId()
    val recipe by recipeDetailViewModel.mutableRecipeState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        recipeDetailViewModel.getRecipeById(detailId)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                elevation = 4.dp,
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        text =  stringResource(id = R.string.recipe_detail_text),
                        color = Color.White,
                        fontSize = 34.sp
                    )

                },
                backgroundColor =  Color(0xFF1BAEEE),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() } ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "arrow back icon", tint = Color.White, modifier = Modifier.size(35.dp))
                    }
                }
            )
        },
        content = {
            Column(
                Modifier
                    .verticalScroll(scrollState)
                    .padding(15.dp)
            ) {
                recipe?.let { recipe ->
                    RecipeDetailScreen(recipe = recipe, patientId, patientViewModel)
                }
            }
        }
    )
}


@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
    patientId: String,
    patientViewModel: PatientViewModel
) {
    val isRecipeInFavorites: Boolean = patientViewModel.favoriteRecipeState.value?.contains(recipe) ?: false
    val context = LocalContext.current

    AsyncImage(
        model =  recipe?.imageLink ?: "",
        contentDescription = "recipe image",
        modifier = Modifier
            .width(900.dp)
            .height(800.dp),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.placeholder)
    )
    Text(text = recipe?.name ?: "recipe name", fontSize = 44.sp)
    Spacer(modifier = Modifier.height(30.dp))

    if (!isRecipeInFavorites) {
        FavoritesButton(patientId = patientId, recipeId = recipe?.recipeId!!, context = context)
    }
    else if (isRecipeInFavorites) {
        Text(text = stringResource(id = R.string.recipe_in_favorites), fontSize = 29.sp)
    }

    Spacer(modifier = Modifier.height(30.dp))
    Text(text = stringResource(id = R.string.ingredients_text), fontSize = 34.sp)
    Spacer(modifier = Modifier.height(30.dp))


    for((key, value) in recipe?.ingredients ?: placeholderText) {
        Text(text = "$key: $value", fontSize = 24.sp)
    }

    Spacer(modifier = Modifier.height(30.dp))
    Text(text = stringResource(id = R.string.steps_text), fontSize = 34.sp)
    Spacer(modifier = Modifier.height(30.dp))
    Text(text = "${recipe?.instructions}", fontSize = 24.sp)
}

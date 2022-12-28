package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.tips
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.PatientViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RecipeViewModel


@Composable
fun RecipeDetailView(
    recipeViewModel: RecipeViewModel,
    detailId: String,
    patientViewModel: PatientViewModel,
    navController: NavController
) {
    Log.d(TAG, "RecipeDetailView passed from recipe list: $detailId")
    val scrollState = rememberScrollState()
    val phoneNumber = AppSession.getPhoneNumber()
    val patient = patientViewModel.getPatientByPhoneNumber(phoneNumber)
    Log.d("patientiddetail", "${patient?.id.toString()}")
    Log.d(TAG, "RecipeDetailView passed from recipe list: $detailId")

    val context = LocalContext.current
//    val recipe = recipeViewModel.recipeListResponse.find { recipe ->
//        detailId == recipe.recipeId
//    }
//    val recipe = recipeViewModel.recipeListResponse.find { recipe ->
//        detailId == recipe.recipeId
//    }

    val recipe = recipeViewModel.getRecipeById(detailId)
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
                        text = recipe.name ?: "",
                        color = Color.White,
                        fontSize = 34.sp
                    )

                },
                backgroundColor =  Color(0xFF1BAEEE),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }, ) {
                        Icon(Icons.Filled.ArrowBack, null, tint = Color.White, modifier = Modifier.size(35.dp))
                    }
                }
            )
        },
        content = {
            Column(
                Modifier
                    .verticalScroll(scrollState)
                    .padding(15.dp)) {
                AsyncImage(
                    model =  recipe.imageLink,
                    contentDescription = "recipe image",
                    modifier = Modifier
                        .width(900.dp)
                        .height(800.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.placeholder)
                )
                Text(text = recipe.name.toString(), fontSize = 44.sp)
                Spacer(modifier = Modifier.height(30.dp))
                if(patient != null) {
                    var isFavorite by remember { mutableStateOf(false) }
                    val recipeHardcodedId = "07f90ce0-a236-497d-3bee-08dadec0f522"
                    val strippedrecipeId = recipeHardcodedId.replace("^\"|\"$", "")
                    IconButton(
                        onClick = {
                            isFavorite = !isFavorite
                            if (isFavorite) {
                                patientViewModel.likeRecipeByPatient(patient.id.toString(), strippedrecipeId, context)
                            }
                            else {
                                // call the remove likeRecipe call from api from liked list
                            }
                        },
                        Modifier
                            .background(Color((0xFF1B7D71)))
                            .clip(RoundedCornerShape(44.dp))
                            .size(75.dp),

                        ) {
                        Icon(
                            imageVector = if (isFavorite) { Icons.Filled.Favorite }
                            else { Icons.Filled.FavoriteBorder},
                            contentDescription = "favorite icon",
                            tint = Color.White,
                            modifier = Modifier.size(44.dp),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Ingredients:", fontSize = 34.sp)
                Spacer(modifier = Modifier.height(30.dp))


                for((key, value) in recipe.ingredients ?: tips) {
                    Text(text = "$key: $value", fontSize = 24.sp)
                }

                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Steps", fontSize = 34.sp)
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "${recipe.instructions}", fontSize = 24.sp)
            }

        }
    )
}
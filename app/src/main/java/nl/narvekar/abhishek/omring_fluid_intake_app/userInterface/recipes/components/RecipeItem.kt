package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.PatientViewModel


@Composable
fun RecipeItem(
    recipe: Recipe,
    patientViewModel: PatientViewModel,
    onClickAction: (Recipe) -> Unit
) {
    Card(
        modifier = Modifier
            // The space between each card and the other
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            AsyncImage(
                //model = R.drawable.recipe_img,
                model = recipe.imageLink,
                contentDescription = "recipe image",
                modifier = Modifier
                    .width(350.dp)
                    .height(350.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
                error = painterResource(R.drawable.placeholder),
            )
            Log.d("recipeimage", "${recipe.imageLink}")
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = recipe.name.toString(),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 35.sp
                )

                Spacer(modifier = Modifier.height(12.dp))
                Row {
//                    if (patient!= null) {
//                        FavoritesButton(patient.id, recipe.recipeId.toString(), patientViewModel, context)
//                    }
                    Spacer(modifier = Modifier.width(38.dp))
                    Button(
                        onClick = {
                              onClickAction(recipe)
                        },
                        shape = RoundedCornerShape(40),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1B7D71)),
                        modifier = Modifier
                            .width(200.dp)
                            .height(70.dp)
                    ) {
                        Text(text = "Instructions", color = Color.White, fontSize = 25.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun FavoritesButton(patientId: String, recipeId: String, patientViewModel: PatientViewModel, context: Context) {
    val strippedrecipeId = recipeId.replace("^\"|\"$", "")
    Log.d("strippedrecipeId", strippedrecipeId)
    var isFavorite by remember { mutableStateOf(false) }
    IconButton(
        onClick = {
            isFavorite = !isFavorite
            if (isFavorite) {
                patientViewModel.likeRecipeByPatient(patientId, strippedrecipeId, context)
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



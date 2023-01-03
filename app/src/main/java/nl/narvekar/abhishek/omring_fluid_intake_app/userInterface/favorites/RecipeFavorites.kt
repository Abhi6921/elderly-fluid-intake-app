package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.favorites

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.FluidTopAppBar
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components.RecipeItem
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.PatientViewModel


@Composable
fun RecipeFavorited(navController: NavController, recipes: List<Recipe>, patientViewModel: PatientViewModel) {
    val context = LocalContext.current

    patientViewModel.getAllLikedRecipes()
    val deletedItems = remember { mutableStateListOf<Recipe>() }
    val patientId = AppSession.getPatientId()
    Scaffold(
        topBar = {
              FluidTopAppBar(topBarTitle = "Favorites")
        },
        content = { innnerPadding ->
            if (recipes.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innnerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "You do not have any favorites yet!", fontSize = 35.sp)
                }
            }
            else {
                LazyColumn(Modifier.padding(innnerPadding)) {
                      itemsIndexed(
                          items =recipes,
                          itemContent = {index, item ->
                              AnimatedVisibility(
                                  visible = !deletedItems.contains(item),
                                  enter = expandVertically(),
                                  exit = shrinkVertically(animationSpec = tween(durationMillis = 2000))
                              ) {
                                  FavoriteRecipeItem(recipe = item, patientViewModel = patientViewModel, deletedItems = deletedItems) {
                                      navController.navigate(Routes.RecipeDetail.route + "/${it.recipeId}")
                                  }
                              }
                          }
                      )


                }
            }

        },
        bottomBar = {
            AppBottomNav(navController = navController)
        }
    )
}

@Composable
fun FavoriteRecipeItem(
    recipe: Recipe,
    patientViewModel: PatientViewModel,
    deletedItems: MutableList<Recipe>,
    onClickAction: (Recipe) -> Unit
) {
    val patientId = AppSession.getPatientId()
    val context = LocalContext.current
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
                    Spacer(modifier = Modifier.width(200.dp))
                    IconButton(
                        onClick = {
                            deletedItems.add(recipe)
                            patientViewModel.removeLikeRecipeByPatient(patientId, recipe.recipeId!!, context)
                        }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete icon",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }
        }
    }
}


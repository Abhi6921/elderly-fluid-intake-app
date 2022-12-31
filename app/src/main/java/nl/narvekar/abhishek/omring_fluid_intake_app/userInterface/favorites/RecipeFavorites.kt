package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components.FavoritesButton
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components.RecipeItem
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.PatientViewModel

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun RecipeFavorited(navController: NavController, recipes: List<Recipe>, patientViewModel: PatientViewModel) {

    val phoneNumber = AppSession.getPhoneNumber()
    val patient = patientViewModel.patientListResponse.find { patientResponse ->
        patientResponse.phoneNumber == phoneNumber
    }
    patientViewModel.getAllLikedRecipes(patient?.id.toString())
    val recipeList = patientViewModel.items.collectAsState()
    val mutableRecipeList = recipeList.value.toMutableList()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                elevation = 4.dp,
                backgroundColor = Color(0xFF1BAEEE),
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        text = "Favorites",
                        color = Color.White,
                        fontSize = 34.sp
                    )
                }
            )
        },
        content = { innnerPadding ->
            if (recipeList.value.isEmpty()) {
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
                      itemsIndexed(items = mutableRecipeList, key= { _, listItem ->
                            listItem.recipeId.toString()
                      }) { index, item ->
                              val state = rememberDismissState(
                                  confirmStateChange = {
                                      if (it == DismissValue.DismissedToStart) {
                                          mutableRecipeList.remove(item)
                                          patientViewModel.removeRecipeByPatient(patient?.id.toString(), item.recipeId!!, context)
                                      }
                                      true
                                  }
                              )
                              SwipeToDismiss(state = state, background = {
                                  val color = when(state.dismissDirection) {
                                      DismissDirection.StartToEnd -> Color.Transparent
                                      DismissDirection.EndToStart -> Color.Red
                                      null -> Color.White
                                  }

                                  Box(
                                      modifier = Modifier
                                          .fillMaxSize()
                                          .background(color = color)
                                          .padding(10.dp)
                                  ) {
                                     Icon(
                                         imageVector = Icons.Default.Delete,
                                         contentDescription = "delete icon",
                                         tint = Color.Gray,
                                         modifier = Modifier.align(Alignment.CenterEnd).size(44.dp),
                                     )
                                  }
                              },
                              dismissContent = {
                                    RecipeItem(recipe = item, patientViewModel = patientViewModel) {
                                        navController.navigate(Routes.RecipeDetail.route + "/${it.recipeId}")
                                    }
                              },
                              directions = setOf(DismissDirection.EndToStart))
                              Divider()
                      }
                }
            }

        },
        bottomBar = {
            AppBottomNav(navController = navController)
        }
    )
}


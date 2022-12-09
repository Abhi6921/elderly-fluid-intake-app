package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav

@Composable
fun RecipeFavorited(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth().height(60.dp),
                elevation = 4.dp,
                title = {
//                    if (recipe!= null) {
//                        Text(text = recipe.name)
//                    }
                },
                backgroundColor =  MaterialTheme.colors.primarySurface
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "This is the recipe favorite page!", fontSize = 35.sp)
            }
        },
        bottomBar = {
            AppBottomNav(navController = navController)
        }
    )

}
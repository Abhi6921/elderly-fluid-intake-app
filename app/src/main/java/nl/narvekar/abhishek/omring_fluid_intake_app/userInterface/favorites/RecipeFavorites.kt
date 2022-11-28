package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav

@Composable
fun RecipeFavorited(navController: NavController) {

    Scaffold(
        topBar = {

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
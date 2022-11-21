package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import nl.narvekar.abhishek.omring_fluid_intake_app.R

@Composable
fun RecipeDetailView() {

    Scaffold(
        topBar = {

        },
        content = {

        }

    )
}

@Preview(showBackground = true)
@Composable
fun RecipeImage() {
    AsyncImage(
        model = "",
        contentDescription = "article",
        modifier = Modifier
            .size(500.dp).height(200.dp),
        placeholder = painterResource(R.drawable.placeholder)
    )
}
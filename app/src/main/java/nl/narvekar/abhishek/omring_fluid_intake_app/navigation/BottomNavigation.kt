package nl.narvekar.abhishek.omring_fluid_intake_app.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Preview(showBackground = true)
@Composable
fun AppBottomNav() {
    BottomNavigation(
        backgroundColor = Color(0xFF1B7FEE)
    ) {
        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "home icon")
            },
            selected = false,
            onClick = {
                // navigate to home page
            },
            label = { Text(Routes.Home.route) },
            unselectedContentColor = Color.White
        )

        BottomNavigationItem(
            icon = {
                 Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite icon")
            },
            selected = false,
            onClick = { /*TODO*/ },
            label = { Text(Routes.Favorite.route)},
            unselectedContentColor = Color.White
        )

        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Filled.LocalDrink, contentDescription = "Favorite icon")
            },
            selected = false,
            onClick = { /*TODO*/ },
            label = { Text(Routes.Drink.route)},
            unselectedContentColor = Color.White
        )

        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Filled.Share, contentDescription = "Favorite icon")
            },
            selected = false,
            onClick = { /*TODO*/ },
            label = { Text(Routes.Share.route)},
            unselectedContentColor = Color.White
        )

    }
}
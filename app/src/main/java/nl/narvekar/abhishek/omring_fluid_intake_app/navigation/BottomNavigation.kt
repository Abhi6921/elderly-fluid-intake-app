package nl.narvekar.abhishek.omring_fluid_intake_app.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun AppBottomNav(navController: NavController) {
    val navBackstackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackstackEntry?.destination?.route

    if (currentRoute == null || currentRoute == Routes.Login.route) {
        return
    }
    val homeSelected = currentRoute == Routes.Home.route
    BottomNavigation(
        backgroundColor = Color(0xFF1B7FEE)
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = if (homeSelected) Icons.Filled.Home else Icons.Default.Home,
                    contentDescription = "home icon"
                )
            },
            selected = false,
            onClick = {
                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Home.route) {
                        inclusive = true
                    }
                }
            },
            label = { Text(Routes.Home.route) },
            unselectedContentColor = Color.White,
            selectedContentColor = Color.Black
        )

        val favoriteSelected = currentRoute == Routes.Favorite.route

        BottomNavigationItem(
            icon = {
                 Icon(
                     imageVector = if (favoriteSelected) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                     contentDescription = "Favorite icon"
                 )
            },
            selected = false,
            onClick = {
                if (!favoriteSelected) {
                    navController.navigate(Routes.Favorite.route)
                }
            },
            label = { Text(Routes.Favorite.route)},
            unselectedContentColor = Color.White,
            selectedContentColor = Color.Black
        )

        val recordsSelected = currentRoute == Routes.Drink.route
        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Filled.LocalDrink, contentDescription = "Local Drink")
            },
            selected = false,
            onClick = {
                if (!recordsSelected) {
                    navController.navigate(Routes.Drink.route)
                }
            },
            label = { Text(Routes.Drink.route)},
            unselectedContentColor = Color.White
        )

        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Filled.Share, contentDescription = "share icon")
            },
            selected = false,
            onClick = { /*TODO*/ },
            label = { Text(Routes.Share.route)},
            unselectedContentColor = Color.White
        )

    }
}
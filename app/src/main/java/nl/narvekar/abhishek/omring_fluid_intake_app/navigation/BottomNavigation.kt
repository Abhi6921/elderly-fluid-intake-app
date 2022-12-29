package nl.narvekar.abhishek.omring_fluid_intake_app.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = Modifier.height(85.dp),
        backgroundColor = Color(0xFF1B7FEE)
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = if (homeSelected) Icons.Filled.Home else Icons.Default.Home,
                    contentDescription = "home icon",
                    tint = if (homeSelected) Color.Black else Color.White,
                    modifier = Modifier.size(44.dp)
                )
                Spacer(modifier = Modifier.height(55.dp))
            },
            selected = false,
            onClick = {
                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Home.route) {
                        inclusive = true
                    }
                }
            },
            label = { Text(Routes.Home.route, fontSize = 23.sp, color = if (homeSelected) Color.Black else Color.White) },
        )

        val favoriteSelected = currentRoute == Routes.Favorite.route

        BottomNavigationItem(
            icon = {
                 Icon(
                     imageVector = if (favoriteSelected) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                     contentDescription = "Favorite icon",
                     tint = if (favoriteSelected) Color.Black else Color.White,
                     modifier = Modifier.size(44.dp)
                 )
                Spacer(modifier = Modifier.height(56.dp))
            },
            selected = false,
            onClick = {
                if (!favoriteSelected) {
                    navController.navigate(Routes.Favorite.route)
                }
            },
            label = { Text(Routes.Favorite.route, fontSize = 23.sp, color = if (favoriteSelected) Color.Black else Color.White)},
        )

        val recordsSelected = currentRoute == Routes.Drink.route
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.LocalDrink,
                    contentDescription = "Local Drink",
                    modifier = Modifier.size(44.dp),
                    tint = if (recordsSelected) Color.Black else Color.White
                )
                Spacer(modifier = Modifier.height(56.dp))
            },
            selected = false,
            onClick = {
                if (!recordsSelected) {
                    navController.navigate(Routes.Drink.route)
                }
            },
            label = { Text(Routes.Drink.route, fontSize = 23.sp, color = if (recordsSelected) Color.Black else Color.White)},
        )
        val shareIconSelected = currentRoute == Routes.Share.route
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "share icon",
                    modifier = Modifier.size(44.dp),
                    tint = if (shareIconSelected) Color.Black else Color.White
                )
                Spacer(modifier = Modifier.height(55.dp))
            },
            selected = false,
            onClick = {
                if (!shareIconSelected) {
                    navController.navigate(Routes.Share.route) {
                        popUpTo(Routes.Home.route) {
                            inclusive = true
                        }
                    }
                }
            },
            label = { Text(Routes.Share.route, fontSize = 23.sp, color = if (shareIconSelected) Color.Black else Color.White)},
        )

    }
}
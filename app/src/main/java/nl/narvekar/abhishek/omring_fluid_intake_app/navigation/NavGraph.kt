package nl.narvekar.abhishek.omring_fluid_intake_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.DashBoardScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.favorites.RecipeFavorited
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.login.LoginUI
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.profile.UserProfilePage
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.RecipeList
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components.RecipeDetailView
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.records.DrinkRecords
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.register.RegisterScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.share.ShareScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.start.StartScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession

const val recipeId = "recipeId"
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val isLoggedIn = AppSession.isLoggedIn()
    NavHost(
        navController = navController,
        startDestination =  if (isLoggedIn) {Routes.Home.route } else { Routes.getStartDestination() }
    ) {

        composable(Routes.Start.route) {
            StartScreen(navController)
        }

        composable(Routes.Login.route) {
            LoginUI(navController)
        }

        composable(Routes.Register.route) {
            RegisterScreen(navController)
        }

        composable(Routes.Home.route) {
            DashBoardScreen(navController)
        }

        composable(Routes.Recipes.route) {
            RecipeList(navController)
        }

        composable(
            route = Routes.RecipeDetail.route + "/{$recipeId}",
            arguments = listOf(navArgument(recipeId) {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->

            RecipeDetailView(
                detailId = navBackStackEntry.arguments!!.getString(recipeId.toString())!!,
                navController = navController
            )
        }
        composable(Routes.Favorite.route) {
            RecipeFavorited(navController)
        }
        composable(Routes.Drink.route) {
            DrinkRecords(navController = navController)
        }

        composable(Routes.Share.route) {
           ShareScreen(navController)
        }
        composable(Routes.Profile.route) {
            UserProfilePage(navController)
        }
    }
}
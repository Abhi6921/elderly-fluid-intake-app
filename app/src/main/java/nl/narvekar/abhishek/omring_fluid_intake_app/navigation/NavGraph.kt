package nl.narvekar.abhishek.omring_fluid_intake_app.navigation

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
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
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.register.components.RegisterConfirmDialog
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.share.ShareScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.start.StartScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.*

const val recipeId = "recipeId"
@Composable
fun AppNavigation(
    recipeViewModel: RecipeViewModel
) {
    val navController = rememberNavController()
    val authToken = AppSession.getAuthToken()
    NavHost(
        navController = navController,
        startDestination =  if (authToken.isEmpty()) { Routes.getDestination() } else { Routes.Home.route }
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
            RecipeList(navController, recipeViewModel)
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
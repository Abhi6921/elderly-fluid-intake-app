package nl.narvekar.abhishek.omring_fluid_intake_app.navigation

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
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
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    recipeViewModel: RecipeViewModel,
    viewModel: CardListViewModel,
    logDrinkViewModel: LogDrinkViewModel,
    patientViewModel: PatientViewModel
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
            LoginUI(loginViewModel, navController)
        }

        composable(Routes.Register.route) {
            RegisterScreen(registerViewModel = registerViewModel, navController)
        }

        composable(Routes.Home.route) {
            DashBoardScreen(navController, logDrinkViewModel, loginViewModel, patientViewModel)
        }

        composable(Routes.Recipes.route) {
            RecipeList(recipes = recipeViewModel.recipeListResponse, navController, patientViewModel, recipeViewModel)
        }

        composable(
            route = Routes.RecipeDetail.route + "/{$recipeId}",
            arguments = listOf(navArgument(recipeId) {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->

            RecipeDetailView(
                recipeViewModel = recipeViewModel,
                detailId = navBackStackEntry.arguments!!.getString(recipeId.toString())!!,
                patientViewModel = patientViewModel,
                navController = navController
            )
        }
        composable(Routes.Favorite.route) {
            RecipeFavorited(navController, patientViewModel.likedRecipeListResponse, patientViewModel)
        }
        composable(Routes.Drink.route) {
            DrinkRecords(navController = navController, viewModel, patientViewModel)
        }

        composable(Routes.Share.route) {
           ShareScreen(navController)
        }
        composable(Routes.Profile.route) {
            UserProfilePage(navController, patientViewModel)
        }
    }
}
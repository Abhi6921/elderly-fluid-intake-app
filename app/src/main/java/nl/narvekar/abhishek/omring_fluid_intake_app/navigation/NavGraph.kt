package nl.narvekar.abhishek.omring_fluid_intake_app.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.DashBoardScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.favorites.RecipeFavorited
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.login.LoginUI
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.RecipeList
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.components.RecipeDetailView
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.records.DrinkRecords
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.register.RegisterScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.start.StartScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.CardListViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LoginViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RecipeViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RegisterViewModel

const val recipeId = "recipeId"
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    recipeViewModel: RecipeViewModel,
    viewModel: CardListViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {

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
            DashBoardScreen(navController)
        }
        composable(Routes.Recipes.route) {
            RecipeList(recipes = recipeViewModel.recipeListResponse, navController)
        }
        composable(
            route = Routes.RecipeDetail.route + "/{$recipeId}",
            arguments = listOf(navArgument(recipeId) {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments!!.getString(
                recipeId)
                ?.let { RecipeDetailView(recipeViewModel = recipeViewModel, detailId = it, navController) }
        }

        composable(Routes.Favorite.route) {
            RecipeFavorited(navController)
        }
        composable(Routes.Drink.route) {
            DrinkRecords(navController = navController, viewModel)
        }
    }
}
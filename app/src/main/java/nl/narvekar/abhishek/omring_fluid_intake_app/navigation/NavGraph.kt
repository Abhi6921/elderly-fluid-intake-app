package nl.narvekar.abhishek.omring_fluid_intake_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.DashBoardScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.login.LoginUI
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.recipes.RecipeList
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.register.RegisterScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.start.StartScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LoginViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RecipeViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RegisterViewModel

@Composable
fun AppNavigation(
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    recipeViewModel: RecipeViewModel
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
    }
}
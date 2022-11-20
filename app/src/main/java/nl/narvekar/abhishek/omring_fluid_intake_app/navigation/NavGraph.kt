package nl.narvekar.abhishek.omring_fluid_intake_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.DashBoardScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.login.LoginUI
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.start.StartScreen
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LoginViewModel

@Composable
fun AppNavigation(loginViewModel: LoginViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.getDestination()) {

        composable(Routes.Start.route) {
            StartScreen(navController)
        }
        composable(Routes.Login.route) {
            LoginUI(loginViewModel, navController)
        }
        composable(Routes.Home.route) {
            DashBoardScreen()
        }
    }
}
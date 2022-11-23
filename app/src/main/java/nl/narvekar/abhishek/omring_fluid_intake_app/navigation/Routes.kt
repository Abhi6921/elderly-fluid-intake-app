package nl.narvekar.abhishek.omring_fluid_intake_app.navigation

import okhttp3.Route

sealed class Routes(val route: String) {
    object Start: Routes("Start")
    object Login: Routes("Login")
    object Register: Routes("Register")
    object Home: Routes("Home")
    object Recipes: Routes("Recipe")
    object RecipeDetail: Routes("RecipeDetail")
    object Favorite: Routes("Favorite")
    object Drink: Routes("Drink Record")
    object Share: Routes("Share")

    companion object {
        fun getDestination() = Start.route
    }

}

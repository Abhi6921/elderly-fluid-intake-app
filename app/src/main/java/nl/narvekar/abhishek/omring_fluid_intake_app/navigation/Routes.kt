package nl.narvekar.abhishek.omring_fluid_intake_app.navigation

sealed class Routes(val route: String) {
    object Start: Routes("Start")
    object Login: Routes("Login")
    object Register: Routes("Register")
    object Home: Routes("Home")
    object Recipes: Routes("Recipe")
    object RecipeDetail: Routes("RecipeDetail")
    object Favorite: Routes("Favorites")
    object Drink: Routes("Drink Record")
    object Share: Routes("Share")
    object Profile: Routes("Profile")

    companion object {
        fun getStartDestination() = Start.route
    }

}

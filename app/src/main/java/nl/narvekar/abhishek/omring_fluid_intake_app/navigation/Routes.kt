package nl.narvekar.abhishek.omring_fluid_intake_app.navigation

sealed class Routes(val route: String) {
    object Home: Routes("Home")
    object Favorite: Routes("Favorite")
    object Drink: Routes("Drink Record")
    object Share: Routes("Share")
}

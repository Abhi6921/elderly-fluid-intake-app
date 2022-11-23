package nl.narvekar.abhishek.omring_fluid_intake_app.data


data class Recipe(
    val recipeId: String,
    val name: String,
    val ingredients: Map<String, String>,
    val instructions: String,
    val imageLink: String
)



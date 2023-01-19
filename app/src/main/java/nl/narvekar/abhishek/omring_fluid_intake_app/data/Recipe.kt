package nl.narvekar.abhishek.omring_fluid_intake_app.data


data class Recipe(
    val recipeId: String? = null,
    val name: String? = null,
    val ingredients: Map<String, String>? = null,
    val instructions: String? = null,
    val visible: Boolean? = true,
    val imageLink: String? = null
)

// placeholder text if there are no ingredients to fetch inside the recipes.
val tips = hashMapOf("Having a balanced diet is the key" to "Have nutritious foods like vegetables and fruits along with legumes, whole wheat, cereals etc."
    , "Fluids will help you manage" to "Drink sufficient water and fluids to maintain the retention of water in your body."
    , "Do not miss prenatal supplements" to "Doctors prescribe prenatal vitamin and mineral supplements for the normal growth and development."
    , "Folic acid is essential" to "During pregnancy, have folic acid (supplement) or folate (natural source of folic acid) to avoid various health problems.")




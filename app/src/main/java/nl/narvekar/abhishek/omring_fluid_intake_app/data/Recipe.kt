package nl.narvekar.abhishek.omring_fluid_intake_app.data


data class Recipe(
    val recipeId: String? = null,
    val name: String? = null,
    val ingredients: Map<String, String>? = null,
    val instructions: String? = null,
    val imageLink: String? = null
)
val tips = hashMapOf("Having a balanced diet is the key" to "Have nutritious foods like vegetables and fruits along with legumes, whole wheat, cereals etc."
    , "Fluids will help you manage" to "Drink sufficient water and fluids to maintain the retention of water in your body."
    , "Do not miss prenatal supplements" to "Doctors prescribe prenatal vitamin and mineral supplements for the normal growth and development."
    , "Folic acid is essential" to "During pregnancy, have folic acid (supplement) or folate (natural source of folic acid) to avoid various health problems.")

//val ALLRECIPELIST = arrayListOf(
//    Recipe(
//        "03ecca14-7a31-11ed-a1eb-0242ac120002",
//        "Lemonade",
//        "Mix everything together",
//        "https://images.unsplash.com/photo-1563713665854-e72327bf780e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80"
//    ),
//    Recipe(
//        "f5f1e548-7a30-11ed-a1eb-0242ac120002",
//        "Tonic Water",
//        "Get Everything to mix together",
//         "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg"
//    ),
//    Recipe(
//        "ea3250d0-7a30-11ed-a1eb-0242ac120002",
//        "Strawberry Limeade",
//        "Get Everything to mix together",
//        "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg"
//    ),
//    Recipe(
//            "391fcfc8-7a32-11ed-a1eb-0242ac120002",
//        "Tonic water",
//        "Get everything to mix together",
//        "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg"
//    ),
//    Recipe(
//        "6280e078-7a32-11ed-a1eb-0242ac120002",
//        "Gin Basil Smash",
//        "Get Everything to mix together",
//        "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg"
//    ),
//    Recipe(
//        "03ecca14-7a31-11ed-a1eb-0242ac120002",
//        "Lemonade",
//        "Mix everything together",
//        "https://images.unsplash.com/photo-1563713665854-e72327bf780e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80"
//    ),
//    Recipe(
//        "f5f1e548-7a30-11ed-a1eb-0242ac120002",
//        "Tonic Water",
//        "Get Everything to mix together",
//        "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg"
//    ),
//    Recipe(
//        "ea3250d0-7a30-11ed-a1eb-0242ac120002",
//        "Strawberry Limeade",
//        "Get Everything to mix together",
//        "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg"
//    ),
//    Recipe(
//        "391fcfc8-7a32-11ed-a1eb-0242ac120002",
//        "Tonic water",
//        "Get everything to mix together",
//        "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg"
//    ),
//    Recipe(
//        "6280e078-7a32-11ed-a1eb-0242ac120002",
//        "Gin Basil Smash",
//        "Get Everything to mix together",
//        "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg"
//    ),
//    Recipe(
//        "03ecca14-7a31-11ed-a1eb-0242ac120002",
//        "Lemonade",
//        "Mix everything together",
//        "https://images.unsplash.com/photo-1563713665854-e72327bf780e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80"
//    ),
//    Recipe(
//        "f5f1e548-7a30-11ed-a1eb-0242ac120002",
//        "Tonic Water",
//        "Get Everything to mix together",
//        "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg"
//    ),
//    Recipe(
//        "ea3250d0-7a30-11ed-a1eb-0242ac120002",
//        "Strawberry Limeade",
//        "Get Everything to mix together",
//        "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg"
//    ),
//    Recipe(
//        "391fcfc8-7a32-11ed-a1eb-0242ac120002",
//        "Tonic water",
//        "Get everything to mix together",
//        "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg"
//    ),
//    Recipe(
//        "6280e078-7a32-11ed-a1eb-0242ac120002",
//        "Gin Basil Smash",
//        "Get Everything to mix together",
//        "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg"
//    )
//)



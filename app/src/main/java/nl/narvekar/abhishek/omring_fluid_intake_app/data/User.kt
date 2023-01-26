package nl.narvekar.abhishek.omring_fluid_intake_app.data

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val active: Boolean,
    val dailyLimit: Int,
    val userRole: Role? = Role("PATIENT"),
    val dailyGoal: Int,
    val dateOfBirth: String,
)

data class Role(
    val role: String
)

// display random motivational quote to user for staying hydrated
val MotivationalQuotes = arrayListOf(
    "drinking water at correct time helps in staying hydrated",
    "drinking water is essential to healthy lifestyle",
    "drinking water cleans your body from inside",
    "if you are not hungry, drink glass of water",
    "water provides the path to cure dehydration",
    "A cup a day keeps the doctor away"
)


package nl.narvekar.abhishek.omring_fluid_intake_app.data

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val dailyLimit: Int,
    //val dateOfBirth: String,
)
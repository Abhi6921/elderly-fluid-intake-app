package nl.narvekar.abhishek.omring_fluid_intake_app.data

data class LoginResponse(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int,
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val dailyLimit: Int,
    val dailyGoal: Int,
)
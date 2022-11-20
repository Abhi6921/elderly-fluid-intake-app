package nl.narvekar.abhishek.omring_fluid_intake_app.data

data class LoginResponse(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)
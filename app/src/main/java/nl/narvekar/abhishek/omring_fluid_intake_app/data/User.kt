package nl.narvekar.abhishek.omring_fluid_intake_app.data

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val active: Boolean? = true,
    val dailyLimit: Int,
    val role: Role? = Role("PATIENT"),
    val dailyGoal: Int,
    val dateOfBirth: String,
)

data class Role(
    val role: String
)


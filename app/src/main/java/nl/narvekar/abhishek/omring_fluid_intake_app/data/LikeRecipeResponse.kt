package nl.narvekar.abhishek.omring_fluid_intake_app.data

data class LikeRecipeResponse (
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val active: Boolean,
    val nurse: Nurse1,
    val dailyLimit: String,
    val userRole: UserRole,
    val recipes: List<Recipe>,
    val dailyGoal: String,
    val dateOfBirth: String
)


data class Nurse1 (
    val id: String
)
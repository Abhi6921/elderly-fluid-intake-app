package nl.narvekar.abhishek.omring_fluid_intake_app.data


data class PatientResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val active: Boolean,
    val nurse: Nurse,
    val dailyLimit: String,
    val userRole: UserRole,
    val recipes: List<Recipe>,
    val dailyGoal: String,
    val dateOfBirth: String
)

data class Nurse (
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String
)
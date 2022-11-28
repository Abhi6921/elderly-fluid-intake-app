package nl.narvekar.abhishek.omring_fluid_intake_app.data

data class LogDrinkResponse(
    val dailyGoal: Int? = null,
    val achieved: Int? = null,
    val dailyLimit: Int? = null,
    val drankNow: Int? = null,
    val amountLeftToLimit: Int? = null
)
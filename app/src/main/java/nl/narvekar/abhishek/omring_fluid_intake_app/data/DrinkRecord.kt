package nl.narvekar.abhishek.omring_fluid_intake_app.data

import java.time.temporal.TemporalAmount
import java.util.Objects

data class DrinkLogResponse(
    val drinkId: String? = null,
    val dateTime: String? = null,
    val amount: Int? = null,
    val patientId: String? = null
)



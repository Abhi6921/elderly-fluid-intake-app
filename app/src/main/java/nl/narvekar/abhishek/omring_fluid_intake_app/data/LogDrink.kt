package nl.narvekar.abhishek.omring_fluid_intake_app.data

import com.google.gson.annotations.SerializedName

data class LogDrink(
    @SerializedName("amount")
    val drinkAmount: Int
)
package nl.narvekar.abhishek.omring_fluid_intake_app.data

import com.google.gson.annotations.SerializedName

data class LogDrinkResponse(
    @SerializedName("dailyGoal")
    val DailyGoal: Int? = null,
    @SerializedName("achieved")
    val Achieved : Int? = null,
    @SerializedName("dailyLimit")
    val DailyLimit : Int? = null,
    @SerializedName("drankNow")
    val DrankNow: Int? = null,
    @SerializedName("amountLeftToLimit")
    val AmountLeftToLimit : Int? = null
)
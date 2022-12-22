package nl.narvekar.abhishek.omring_fluid_intake_app.data

import com.google.gson.annotations.SerializedName

data class LogDrinkResponse(
    @SerializedName("dailyGoal")
    val DailyGoal: Int? = null,
    @SerializedName("achieved")
    val achieved : Int? = null,
    @SerializedName("dailyLimit")
    val dailyLimit : Int? = null,
    @SerializedName("drankNow")
    var drankNow: Int? = null,
    @SerializedName("amountLeftToLimit" ) var amountLeftToLimit : Int? = null
)
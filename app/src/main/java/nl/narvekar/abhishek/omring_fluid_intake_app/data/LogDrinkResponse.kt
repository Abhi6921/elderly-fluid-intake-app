package nl.narvekar.abhishek.omring_fluid_intake_app.data

import com.google.gson.annotations.SerializedName

data class LogDrinkResponse(
    @SerializedName("dailyGoal"         ) var dailyGoal         : Int? = null,
    @SerializedName("achieved"          ) var achieved          : Int? = null,
    @SerializedName("dailyLimit"        ) var dailyLimit        : Int? = null,
    @SerializedName("drankNow"          ) var drankNow          : Int? = null,
    @SerializedName("amountLeftToLimit" ) var amountLeftToLimit : Int? = null
)
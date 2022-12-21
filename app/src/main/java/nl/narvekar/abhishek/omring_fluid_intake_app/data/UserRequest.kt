package nl.narvekar.abhishek.omring_fluid_intake_app.data

import com.google.gson.annotations.SerializedName

data class UserRequest(

    @SerializedName("firstName")
    val FirstName: String,
    @SerializedName("lastName")
    val LastName: String,
    @SerializedName("email")
    val Email: String,
    @SerializedName("phoneNumber")
    val PhoneNumber: String,
    @SerializedName("password")
    val Password: String,
    @SerializedName("active")
    val Active: Boolean,
    @SerializedName("dailyLimit")
    val DailyLimit: Int,
    @SerializedName("careGiver")
    val CareGiver: String? = null,
    @SerializedName("recipes")
    val Recipes: String? = null,
    @SerializedName("userRole")
    val UserRole: UserRole? = UserRole(),
    @SerializedName("dailyGoal")
    val DailyGoal: Int,
    @SerializedName("dateOfBirth")
    val DateOfBirth: String,
    @SerializedName("careGiverId")
    val CareGiverId: String

)

data class UserRole (
    @SerializedName("role" ) val role : String? = null
)
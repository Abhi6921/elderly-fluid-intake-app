package nl.narvekar.abhishek.omring_fluid_intake_app.data

import com.google.gson.annotations.SerializedName

data class UserRequest(

    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("phoneNumber")
    val phoneNumber: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("active")
    val active: Boolean?  = null,
    @SerializedName("dailyLimit")
    val dailyLimit: Int? = null,
    @SerializedName("careGiver")
    val careGiver: String? = null,
    @SerializedName("recipes")
    val recipes: String? = null,
    @SerializedName("userRole")
    val userRole: UserRole? = UserRole(),
    @SerializedName("dailyGoal")
    val dailyGoal: Int? = null,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String? = null,
    @SerializedName("careGiverId")
    val careGiverId: String? = null

)

data class UserRole (
    @SerializedName("role" ) val role : String? = null
)
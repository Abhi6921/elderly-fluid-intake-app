package nl.narvekar.abhishek.omring_fluid_intake_app.data

import com.google.gson.annotations.SerializedName


data class UserResponse (

    @SerializedName("id")
    val id: String? = null,
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("phoneNumber")
    val phoneNumber : String? = null,
    @SerializedName("active")
    val active: Boolean? = null,
    @SerializedName("dailyLimit")
    val dailyLimit  : Int? = null,
    @SerializedName("userRole")
    val userRole: UserRole? = UserRole(),
    @SerializedName("recipes")
    val recipes : ArrayList<String> = arrayListOf(),
    @SerializedName("dailyGoal")
    val dailyGoal:Int? = null,
    @SerializedName("dateOfBirth")
    val dateOfBirth : String? = null

)

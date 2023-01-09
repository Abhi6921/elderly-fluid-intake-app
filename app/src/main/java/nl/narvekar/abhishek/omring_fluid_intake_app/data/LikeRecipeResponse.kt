package nl.narvekar.abhishek.omring_fluid_intake_app.data

import com.google.gson.annotations.SerializedName

data class LikeRecipeResponse (
    @SerializedName("id"          ) var id          : String?            = "",
    @SerializedName("firstName"   ) var firstName   : String?            = "",
    @SerializedName("lastName"    ) var lastName    : String?            = "",
    @SerializedName("email"       ) var email       : String?            = "",
    @SerializedName("phoneNumber" ) var phoneNumber : String?            = "",
    @SerializedName("active"      ) var active      : Boolean?           = false,
    @SerializedName("nurse"       ) var nurse       : Nurse?             = Nurse(),
    @SerializedName("dailyLimit"  ) var dailyLimit  : Int?               = 0,
    @SerializedName("userRole"    ) var userRole    : UserRole?          = UserRole(),
    @SerializedName("recipes"     ) var recipes     : ArrayList<Recipe> = arrayListOf(),
    @SerializedName("dailyGoal"   ) var dailyGoal   : Int?               = 0,
    @SerializedName("dateOfBirth" ) var dateOfBirth : String?            = ""
)
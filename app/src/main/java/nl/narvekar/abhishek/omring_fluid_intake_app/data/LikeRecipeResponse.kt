package nl.narvekar.abhishek.omring_fluid_intake_app.data

import com.google.gson.annotations.SerializedName

data class LikeRecipeResponse (
    @SerializedName("id"          ) val id          : String?            = "",
    @SerializedName("firstName"   ) val firstName   : String?            = "",
    @SerializedName("lastName"    ) val lastName    : String?            = "",
    @SerializedName("email"       ) val email       : String?            = "",
    @SerializedName("phoneNumber" ) val phoneNumber : String?            = "",
    @SerializedName("active"      ) val active      : Boolean?           = false,
    @SerializedName("nurse"       ) val nurse       : Nurse?             = Nurse(),
    @SerializedName("dailyLimit"  ) val dailyLimit  : Int?               = 0,
    @SerializedName("userRole"    ) val userRole    : UserRole?          = UserRole(),
    @SerializedName("recipes"     ) val recipes     : ArrayList<Recipe> = arrayListOf(),
    @SerializedName("dailyGoal"   ) val dailyGoal   : Int?               = 0,
    @SerializedName("dateOfBirth" ) val dateOfBirth : String?            = ""
)
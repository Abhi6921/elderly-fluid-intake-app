package nl.narvekar.abhishek.omring_fluid_intake_app.data

import com.google.gson.annotations.SerializedName


data class Nurse (
    @SerializedName("id"          ) var id          : String? = null,
    @SerializedName("firstName"   ) var firstName   : String? = null,
    @SerializedName("lastName"    ) var lastName    : String? = null,
    @SerializedName("email"       ) var email       : String? = null,
    @SerializedName("phoneNumber" ) var phoneNumber : String? = null
)
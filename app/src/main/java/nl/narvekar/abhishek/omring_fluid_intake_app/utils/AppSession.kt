package nl.narvekar.abhishek.omring_fluid_intake_app.utils

import android.content.Context
import android.content.SharedPreferences
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.ACHIEVED
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.AUTH_TOKEN_KEY
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.DAILYLIMIT
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.FIRSTNAME
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.ID
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.IS_LOGGED_IN
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.LASTNAME
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.PASSWORD
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.USERNAME

object AppSession {

    private lateinit var sharedPreferences: SharedPreferences

    fun startSession(context: Context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    }

    fun getAuthToken() : String {
        val authToken = sharedPreferences.getString(AUTH_TOKEN_KEY, "") ?: ""
        return authToken
    }
    fun isUserLoggedIn() : Boolean {
        val loggedIn = sharedPreferences.getBoolean(IS_LOGGED_IN, false)
        return loggedIn
    }

    fun getFirstName() : String {
        val firstName = sharedPreferences.getString(FIRSTNAME, "") ?: ""
        return firstName
    }

    fun getLastName() : String {
        val lastName = sharedPreferences.getString(LASTNAME, "") ?: ""
        return lastName
    }

    fun getDailyLimit() : Int {
        val dailyLimit = sharedPreferences.getInt(DAILYLIMIT, 0)
        return dailyLimit
    }

    fun getPhoneNumber() : String {
        return sharedPreferences.getString(USERNAME, "") ?: ""
    }

    fun removeAchievedIntake() {
        sharedPreferences.edit().apply {
            putFloat(ACHIEVED, 0f)
        }.apply()
    }

    fun saveUserData(username: String,
                     password: String,
                     authToken: String,
                     patientId: String,
                     firstname: String,
                     lastname: String,
                     dailyLimit: Int
    ) {
        sharedPreferences.edit().apply {
            putString(USERNAME, username)
            putString(PASSWORD, password)
            putString(ID, patientId)
            putString(FIRSTNAME, firstname)
            putString(LASTNAME, lastname)
            putInt(DAILYLIMIT, dailyLimit)
            putString(AUTH_TOKEN_KEY, authToken)
            putBoolean(IS_LOGGED_IN, true)
        }.apply()
    }

    fun removeUserData() {
        sharedPreferences.edit().apply {
            putString(USERNAME, "")
            putString(PASSWORD, "")
            putString(ID, "")
            putString(FIRSTNAME, "")
            putString(LASTNAME, "")
            putInt(DAILYLIMIT, 0)
            putString(AUTH_TOKEN_KEY, "")
            putBoolean(IS_LOGGED_IN, false)
        }.apply()
    }
}
package nl.narvekar.abhishek.omring_fluid_intake_app.utils

import android.content.Context
import android.content.SharedPreferences
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.AUTH_TOKEN_KEY
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.IS_LOGGED_IN
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.PASSWORD
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.TODAY_INTAKE
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

    fun getPhoneNumber() : String {
        return sharedPreferences.getString(USERNAME, "") ?: ""
    }

    fun saveTodaysIntake(drinkAmount: Float) {
        return sharedPreferences.edit().apply {
            putFloat(TODAY_INTAKE, drinkAmount)
        }.apply()
    }


    fun saveUserData(username: String, password: String, authToken: String) {
        sharedPreferences.edit().apply {
            putString(USERNAME, username)
            putString(PASSWORD, password)
            putString(AUTH_TOKEN_KEY, authToken)
            putBoolean(IS_LOGGED_IN, true)
        }.apply()
    }

    fun removeUserData() {
        sharedPreferences.edit().apply {
            putString(USERNAME, "")
            putString(PASSWORD, "")
            putString(AUTH_TOKEN_KEY, "")
            putBoolean(IS_LOGGED_IN, false)
        }.apply()
    }
}
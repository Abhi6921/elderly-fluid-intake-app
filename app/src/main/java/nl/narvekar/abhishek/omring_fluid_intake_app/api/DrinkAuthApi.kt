package nl.narvekar.abhishek.omring_fluid_intake_app.api

import nl.narvekar.abhishek.omring_fluid_intake_app.data.Login
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DrinkAuthApi {

    @POST("api/v1/login")
    fun loginUser(@Body login: Login) : Call<LoginResponse>

    companion object {
        var apiService: DrinkAuthApi? = null
        fun getInstance() : DrinkAuthApi {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://drinkappauthentication.azurewebsites.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(DrinkAuthApi::class.java)
            }
            return apiService!!
        }
    }
}
package nl.narvekar.abhishek.omring_fluid_intake_app.api

import nl.narvekar.abhishek.omring_fluid_intake_app.data.Login
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface DrinkApi {

    @Headers("Content-Type:application/json")
    @POST("/v1/login")
    suspend fun login(@Body login: Login) : Response<LoginResponse>

    companion object {
        var apiService: DrinkApi? = null
        fun getInstance() : DrinkApi {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://drinkappauthentication.azurewebsites.net/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(DrinkApi::class.java)
            }
            return apiService!!
        }
    }
}
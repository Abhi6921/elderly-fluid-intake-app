package nl.narvekar.abhishek.omring_fluid_intake_app.api

import nl.narvekar.abhishek.omring_fluid_intake_app.data.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UsersAuthApi {

    @Headers("Content-Type:application/json")
    @POST("api/v1/patients")
    fun registerUser(@Body user: User) : Call<User>

    companion object {
        var apiService: UsersAuthApi? = null
        fun getInstance() : UsersAuthApi {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://drinkappusers.azurewebsites.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(UsersAuthApi::class.java)
            }
            return apiService!!
        }
    }
}
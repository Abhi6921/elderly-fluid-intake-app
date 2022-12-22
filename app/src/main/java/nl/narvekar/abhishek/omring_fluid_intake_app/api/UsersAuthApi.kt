package nl.narvekar.abhishek.omring_fluid_intake_app.api

import nl.narvekar.abhishek.omring_fluid_intake_app.data.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface UsersAuthApi {

    @POST("api/v1/patients")
    fun registerUser(@Body user: UserRequest) : Call<UserResponse>

    @Headers("Content-Type:application/json")
    //@Headers("Accept: application/json")
    @POST("api/v1/patients/logdrink")
    suspend fun postNewDrink(@Header("Authorization") authToken: String, @Body drinkAmount: LogDrink) : Call<LogDrinkResponse>

    @GET("api/v1/patients")
    suspend fun getAllPatients(@Header("Authorization") authToken: String) : ArrayList<PatientResponse>

    companion object {
        var apiUserService: UsersAuthApi? = null
        fun getUsersAuthApiInstance() : UsersAuthApi {
            if (apiUserService == null) {
                apiUserService = Retrofit.Builder()
                    .baseUrl("https://da-users.azurewebsites.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(UsersAuthApi::class.java)
            }
            return apiUserService!!
        }
    }
}
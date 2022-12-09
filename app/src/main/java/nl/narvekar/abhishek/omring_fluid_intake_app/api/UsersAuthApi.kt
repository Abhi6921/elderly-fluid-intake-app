package nl.narvekar.abhishek.omring_fluid_intake_app.api

import nl.narvekar.abhishek.omring_fluid_intake_app.data.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.HEAD
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface UsersAuthApi {

    @Headers("Content-Type: application/json")
    @POST("api/v1/patients")
    fun registerUser(@Body user: UserRequest) : Call<UserResponse>

//    @Headers("Content-Type:application/json")
    @Headers("Accept: application/json")
    @POST("api/v1/patients/logdrink")
    fun postNewDrink(@Header("Bearer") authToken: String, @Body drinkAmount: LogDrink) : Call<LogDrinkResponse>

    fun getPatientById(@Header("Bearer") authToken: String, @Query("id") patientID: String) : UserResponse

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
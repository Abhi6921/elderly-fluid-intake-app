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
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersAuthApi {

    @POST("api/v1/patients")
    fun registerUser(@Body user: UserRequest) : Call<UserResponse>

    @Headers("Content-Type:application/json")
    //@Headers("Accept: application/json")
    @POST("api/v1/patients/logdrink")
    fun postNewDrink(@Header("Authorization") authToken: String, @Query("amount")  drinkAmount: Int) : Call<LogDrinkResponse>

    @GET("api/v1/patients")
    suspend fun getAllPatients(@Header("Authorization") authToken: String) : ArrayList<PatientResponse>

    // admin token: eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOlsiQURNSU4iLCJDQVJFX0dJVkVSIl0sImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiIrMzE2NDU4MjYwMDAiLCJuYmYiOjE2NzE4ODIwNTEsImV4cCI6MTcwMzQxODA1MSwiaWF0IjoxNjcxODgyMDUxLCJpc3MiOiJEcmlua0FwcFJlY2lwZXMuYXp1cmV3ZWJzaXRlcy5uZXQiLCJhdWQiOiJEcmlua0FwcFVzZXJzIC8gUGF0aWVudHMgLyBDYXJlZ2l2ZXJzIC8gQWRtaW5zIn0.X-lYgIhpqPXLf9lnxp3IiF1XLPCiN0Dtms2E7Ymc8-I"
    @GET("api/v1/patients/drinks/today/{id}")
    suspend fun getCurrentFluidStatus(@Header("Authorization") authToken: String, @Path("id") patientId: String) : LogDrinkResponse

    // endpoint under construction from back-end side
    @POST("api/v1/patients/{patientId}/likeRecipe")
    suspend fun likeRecipeByPatient(@Header("Authorization") authToken: String, @Path("patientId") patientId: String, @Body recipeId: String) : Response<PatientResponse>

    @GET("api/v1/patients/drinks/{patientId}")
    suspend fun getPatientDrinkLogs(@Header("Authorization") authToken: String, @Path("patientId") patientId: String) : Response<List<DrinkLogResponse>>

    @GET("api/v1/patients/recipes/{id}")
    fun fetchAllLikedRecipes(@Header("Authorization") authToken: String, @Path("id") patientId: String) : List<Recipe>

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
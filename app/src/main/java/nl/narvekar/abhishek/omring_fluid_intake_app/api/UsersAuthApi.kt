package nl.narvekar.abhishek.omring_fluid_intake_app.api

import nl.narvekar.abhishek.omring_fluid_intake_app.data.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
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
    suspend fun getAllPatients(@Header("Authorization") authToken: String, @Query("limit") listLimit: Int) : List<PatientResponse>

    @GET("api/v1/patients/drinks/today/{id}")
    suspend fun getCurrentFluidStatus(@Header("Authorization") authToken: String, @Path("id") patientId: String) : LogDrinkResponse
    // is string expected in body of this endpoint

    @GET("api/v1/patients/drinks/{patientId}")
    suspend fun getPatientDrinkLogs(
        @Header("Authorization") authToken: String,
        @Path("patientId") patientId: String,
        @Query("from") dateFrom: String,
        @Query("to") dateTo : String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ) : Response<List<DrinkLogResponse>>

    @GET("api/v1/patients/recipes/{id}")
    suspend fun fetchAllLikedRecipes(@Header("Authorization") authToken: String, @Path("id") patientId: String) : List<Recipe>

    @Headers("Content-Type:application/json")
    @POST("api/v1/patients/{patientId}/likeRecipe")
    fun likeRecipeByPatient(@Header("Authorization") authToken: String, @Path("patientId") patientId: String, @Body recipeId: String) : Call<LikeRecipeResponse>

    @POST("api/v1/patients/{patientId}/unlikeRecipe")
    fun removeRecipeFromFavorites(@Header("Authorization") authToken: String, @Path("patientId") patientId: String, @Body recipeId: String) : Call<LikeRecipeResponse>

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
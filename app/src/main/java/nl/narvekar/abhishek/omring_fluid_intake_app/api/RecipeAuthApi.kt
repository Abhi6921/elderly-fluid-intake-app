package nl.narvekar.abhishek.omring_fluid_intake_app.api

import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RecipeAuthApi {

    @GET("api/v1/recipes")
    suspend fun getAllRecipes() : List<Recipe>

    companion object {
        var apiService: RecipeAuthApi? = null
        fun getInstance() : RecipeAuthApi {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://da-recipes.azurewebsites.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(RecipeAuthApi::class.java)
            }
            return apiService!!
        }
    }
}
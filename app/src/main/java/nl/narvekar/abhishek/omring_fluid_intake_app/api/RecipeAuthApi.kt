package nl.narvekar.abhishek.omring_fluid_intake_app.api

import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface RecipeAuthApi {

    @GET("api/v1/recipes")
    suspend fun getAllRecipes() : List<Recipe>

    @GET("api/v1/recipes/{id}")
    suspend fun getRecipeById(@Path("id") recipeId: String) : Recipe

    companion object {
        var apiService: RecipeAuthApi? = null
        fun getInstance() : RecipeAuthApi {
            var httpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            var mOkHttpClient = OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build()
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://da-recipes.azurewebsites.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build().create(RecipeAuthApi::class.java)
            }
            return apiService!!
        }
    }
}
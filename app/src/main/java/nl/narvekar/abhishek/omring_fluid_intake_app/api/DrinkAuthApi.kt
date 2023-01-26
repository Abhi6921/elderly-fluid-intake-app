package nl.narvekar.abhishek.omring_fluid_intake_app.api

import nl.narvekar.abhishek.omring_fluid_intake_app.data.Login
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface DrinkAuthApi {

    @Headers("Content-Type:application/json")
    @POST("api/v1/login")
    fun loginUser(@Body login: Login) : Call<LoginResponse>

    companion object {
        var apiService: DrinkAuthApi? = null

        fun getInstance() : DrinkAuthApi {
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
                    .baseUrl("https://da-authentication.azurewebsites.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build().create(DrinkAuthApi::class.java)
            }
            return apiService!!
        }
    }
}

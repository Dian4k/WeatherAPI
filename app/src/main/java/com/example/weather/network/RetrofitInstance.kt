package com.example.weather.network
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit

object RetrofitInstance {
    private const val BASE_URL = "https://api.weatherbit.io/v2.0/"

    val apiService: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
}
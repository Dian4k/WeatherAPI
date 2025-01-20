package com.example.weather.network
import com.example.weather.data.WeatherResponse
import retrofit2.http.Query
import retrofit2.http.GET
import retrofit2.Response


//retrofit отправляет запрос на сервер
//url строится на из базового адреса и указанных параметров (город и апи ключ)

interface WeatherApiService {
    @GET("current")
    suspend fun getCurrentWeather(
        @Query("city") city: String,
        @Query("key") apiKey: String
    ): Response<WeatherResponse>

    @GET("forecast/daily")
    suspend fun getForecastWeather(
        @Query("city") city: String,
        @Query("key") apiKey: String
    ): Response<WeatherResponse>
}

//ответ сервера возвращается в виде объекта Response<WeatherResponse>
//Response - обёртка, содержащая НТТР-ответ (статус, тело ответа и тд)
//WeatherResponse - тип данных, в который retrofit преобразует json-ответ
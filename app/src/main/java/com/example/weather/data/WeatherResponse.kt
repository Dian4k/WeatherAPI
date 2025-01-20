package com.example.weather.data

data class WeatherResponse(
    val data: List<WeatherData>
)

data class WeatherData(
    val city_name: String,
    val temperature: Double,
    val apparent_temperature: Double,
    val wind: Double,
    val pressure: Double,
    val uv_index: Double,
    val weather: Weather
)

data class Weather(
    val description: String,
    val icon: String
)
package com.example.weather

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var weatherViewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        weatherViewModel.weatherData.observe(this, Observer { weatherResponse ->
            //Обновление UI с данными о погоде
            val temperature = weatherResponse.data[0].temperature
            findViewById<TextView>(R.id.temperatureTextView).text = "$temperature °C"
        })

        //Наблюдаем за ошибками
        weatherViewModel.error.observe(this, Observer { error ->
            //Обработка ошибок, например, вывод сообщения
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })

        //Вызов функции для загрузки данных о погоде
        val city = "London"
        val apiKey = "your_api_key"
        weatherViewModel.fetchCurrentWeather(city, apiKey)



    }
}
package com.example.weather
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import com.example.weather.data.WeatherResponse
import com.example.weather.network.RetrofitInstance
import kotlinx.coroutines.launch



class WeatherViewModel: ViewModel() {
    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> get() = _weatherData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchCurrentWeather(city: String, apiKey: String){
        viewModelScope.launch{
            try{
                val response = RetrofitInstance.apiService.getCurrentWeather(city, apiKey)
                if(response.isSuccessful){
                    _weatherData.postValue(response.body())
                } else{
                    _error.postValue("Ошибка: ${response.message()}")
                }
            } catch(e: Exception){
                _error.postValue("Ошибка сети: ${e.message}")
            }
        }
    }
}
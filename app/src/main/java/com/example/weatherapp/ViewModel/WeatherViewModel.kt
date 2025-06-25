package com.example.weatherapp.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Model.WeatherResponse
import com.example.weatherapp.Retrofit.RetroiftClient
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel() {
    var weatherResponse by mutableStateOf<WeatherResponse?>(null)
    private set

    var errorMessage by mutableStateOf<String?>(null)


    fun getWeather(city:String){
        viewModelScope.launch {

            try{
                val response = RetroiftClient.api.getWheather(
                    apiKey = "baaad72f6c9448f39f3230409252306",
                    city = city
                )

                if(response.isSuccessful){

                    weatherResponse = response.body()
                    errorMessage = null
                    Log.d("API", "udało się pobrać dane ${weatherResponse} ")
                }else{
                    errorMessage = "Błąd: ${response.code()}"
                }
            }catch (e: Exception){
                Log.d("API", "nie udało sie pobrać danych ${e.message} ")
                errorMessage = "Wyjątek: ${e.message}"
            }

        }
    }

}
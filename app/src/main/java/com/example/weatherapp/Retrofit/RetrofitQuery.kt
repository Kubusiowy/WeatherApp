package com.example.weatherapp.Retrofit

import com.example.weatherapp.Model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi{

    @GET("current.json")
    suspend fun getWheather(
        @Query("key") apiKey:String,
        @Query("q") city:String,
        @Query("lang") lang:String = "pl"
    ):Response<WeatherResponse>
}
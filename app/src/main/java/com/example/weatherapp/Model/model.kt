package com.example.weatherapp.Model


data class WeatherResponse(
    val location: Location,
    val current: Current
)

data class Location(
    val name:String,
    val country:String,
    val localtime:String
)

data class Current(
    val temp_c: Double,
    val temp_f: Double,
    val condition: Condition,
    val humidity:Int,
    val wind_mph: Double,
    val wind_kph: Double,
    val pressure_mb: Double,
    val wind_dir: Char
)

data class Condition(
    val text:String,
    val icon:String
)
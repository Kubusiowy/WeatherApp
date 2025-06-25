package com.example.weatherapp.Screens

sealed class Screens(val route:String) {
    object HomeScreen: Screens(route = "Home_Screen")
}
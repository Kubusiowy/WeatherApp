package com.example.weatherapp.PrefsHelper

import android.content.Context

object PrefsHelper{
    private const val PREF_NAME = "weather_prefs"
    private const val KEY_CITY = "last_city"

    fun saveCity(context:Context, city:String){
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_CITY,city).apply()
    }

    fun getCity(context:Context):String?{
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_CITY, null)
    }



}
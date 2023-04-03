package com.example.weatherapp.utlis

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class WeatherPreference(context : Context) {
    private val USER_PREFS = "weather_prefs"
    private val latitude = "Latitude"
    private val longitude = "Longitude"
    private val cityName = "CityName"
    private var appSharedPrefs: SharedPreferences = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE)
    private var prefsEditor: SharedPreferences.Editor = appSharedPrefs.edit()

    fun setLatitude(latitude: String?) {
        prefsEditor.putString(this.latitude, latitude).apply()
    }

    fun getLatitude(): String? {
        return appSharedPrefs.getString(latitude, "")
    }

    fun setCityName(cityName:String?) {
        prefsEditor.putString(this.cityName, cityName).apply()
    }


    fun getcity(): String? {
        return appSharedPrefs.getString(cityName, "")
    }

    fun setLongitude(longitude: String?) {
        prefsEditor.putString(this.longitude, longitude).apply()
    }

    fun getLongitude(): String? {
        return appSharedPrefs.getString(longitude, "")
    }
}
package com.example.weatherapp.apiservices

import com.example.weatherapp.model.ListModel
import com.example.weatherapp.model.WeatherList
import com.example.weatherapp.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call;

interface ApiInterface {

    @GET("weather?units=metric")
    fun getWeather(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("appid") appid: String?
    ): Call<WeatherModel?>?

    @GET("forecast?units=metric")
    fun getForecast(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("appid") appid: String?
    ): Call<WeatherList?>?

    @GET("weather?units=metric")
    fun getCityWeather(
        @Query("q") q: String?,
        @Query("appid") appid: String?
    ): Call<WeatherModel?>?
}
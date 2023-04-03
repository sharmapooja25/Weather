package com.example.weatherapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.model.ListModel
import com.example.weatherapp.model.WeatherList
import com.example.weatherapp.model.WeatherModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HomeViewModel(application: Application) :
    AndroidViewModel(application) {

    var locationresponse: MutableLiveData<WeatherModel?> = MutableLiveData<WeatherModel?>()
    var forevastresponse: MutableLiveData<WeatherList?> = MutableLiveData<WeatherList?>()
    var cityweatherresponse: MutableLiveData<WeatherModel?> = MutableLiveData<WeatherModel?>()
    var cityforevastresponse: MutableLiveData<WeatherList?> = MutableLiveData<WeatherList?>()


    fun setlocationCall(lat: String,long: String,appid:String) {
        val call =
            getApplication<WeatherApp>().apiService.getWeather(lat,long,appid)
        call!!.enqueue(object : Callback<WeatherModel?> {
            override fun onResponse(
                call: Call<WeatherModel?>,
                response: Response<WeatherModel?>
            ) {
                Log.e("responsebody",response.body().toString())
                if (response!= null)

                    locationresponse.setValue(response.body())

                else if (response == null) {
                    val gson = Gson()
                    try {
                        val response1 = gson.fromJson(
                            response.errorBody()!!.string(),
                            WeatherModel::class.java
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(
                call: Call<WeatherModel?>,
                t: Throwable) {
                locationresponse.value = null
            }
        })
    }

    val locationCall: LiveData<WeatherModel?>
        get() = locationresponse


    fun setForecastCall(lat: String,long: String,appid:String) {
        val call =
            getApplication<WeatherApp>().apiService.getForecast(lat,long,appid)
        call!!.enqueue(object : Callback<WeatherList?> {
            override fun onResponse(
                call: Call<WeatherList?>,
                response: Response<WeatherList?>
            ) {
                Log.e("responsebody",response.body().toString())
                if (response!= null)

                    forevastresponse.setValue(response.body())

                else if (response == null) {
                    val gson = Gson()
                    try {
                        val response1 = gson.fromJson(
                            response.errorBody()!!.string(),
                            WeatherModel::class.java
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(
                call: Call<WeatherList?>,
                t: Throwable) {
                forevastresponse.value = null
            }
        })
    }

    val forecastCall: LiveData<WeatherList?>
        get() = forevastresponse




    fun setCityweatherCall(cityname:String,appid: String) {
        val call =
            getApplication<WeatherApp>().apiService.getCityWeather(cityname,appid)
        call!!.enqueue(object : Callback<WeatherModel?> {
            override fun onResponse(
                call: Call<WeatherModel?>,
                response: Response<WeatherModel?>
            ) {
                Log.e("responsebody",response.body().toString())
                if (response!= null)

                    cityweatherresponse.setValue(response.body())

                else if (response == null) {
                    val gson = Gson()
                    try {
                        val response1 = gson.fromJson(
                            response.errorBody()!!.string(),
                            WeatherModel::class.java
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(
                call: Call<WeatherModel?>,
                t: Throwable) {
                cityweatherresponse.value = null
            }
        })

    }

    val ciryWeatherCall: LiveData<WeatherModel?>
        get() = cityweatherresponse

}
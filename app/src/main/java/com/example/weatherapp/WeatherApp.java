package com.example.weatherapp;

import android.app.Application;
import android.content.Context;

import com.example.weatherapp.apiservices.ApiInterface;
import com.example.weatherapp.apiservices.NetworkUtility;

public class WeatherApp extends Application {

    private Context mContext;
    private WeatherApp mInstance;
    private String TAG = WeatherApp.class.getName();

    private ApiInterface mApiService;
    private String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private String IMAGE_URL = "https://openweathermap.org/img/wn/";

    private String APP_ID = "f10032af2af94e79378c7a53b6260b6e";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mInstance = this;
        basicSetupForAPI();
    }

    public ApiInterface getApiService() {
        return mApiService;
    }

    public Context getAppContext() {
        return mContext;
    }

    public WeatherApp getmInstance() {
        return mInstance;
    }

    public String getImageUrl() {
        return IMAGE_URL;
    }
    public String getAPPID() {
        return APP_ID;
    }



    public void basicSetupForAPI() {
        NetworkUtility.Builder networkBuilder = new NetworkUtility.Builder(
                (BASE_URL), mContext);
        networkBuilder.withConnectionTimeout(60)
                .withReadTimeout(10)
                .withShouldRetryOnConnectionFailure(true)
                .build();
        mApiService = networkBuilder.getRetrofit().create(ApiInterface.class);
    }
}

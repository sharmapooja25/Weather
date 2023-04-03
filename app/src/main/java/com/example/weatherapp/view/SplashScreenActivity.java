package com.example.weatherapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.R;
import com.example.weatherapp.base.BaseActivity;
import com.example.weatherapp.base.GPSTracker;
import com.example.weatherapp.utlis.WeatherPreference;

public class SplashScreenActivity extends AppCompatActivity{
     private GPSTracker gpsTracker;
    WeatherPreference preference = null;
     double latitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the splash screen activity
        setContentView(R.layout.activity_splash_screen);
        gpsTracker = new GPSTracker(this);
        preference = new WeatherPreference(this);

        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
            preference.setLatitude(String.valueOf(latitude));
            preference.setLongitude(String.valueOf(longitude));

            Log.e("latitude", String.valueOf(latitude));
            Log.e("longitude", String.valueOf(longitude));
            // Do something with latitude and longitude
        } else {
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gpsTracker.showSettingsAlert();
        }
        // Start a new thread to load the main activity in the background
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(
                        SplashScreenActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000); // Set the time delay for the splash screen in milliseconds
    }
}

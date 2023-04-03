package com.example.weatherapp.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.WeatherApp;
import com.example.weatherapp.adapter.ForecastAdapter;
import com.example.weatherapp.base.GPSTracker;
import com.example.weatherapp.databinding.ActivitySearchBinding;
import com.example.weatherapp.model.WeatherList;
import com.example.weatherapp.model.WeatherModel;
import com.example.weatherapp.utlis.WeatherPreference;
import com.example.weatherapp.viewModel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private GPSTracker gpsTracker;
    private ActivitySearchBinding searchBinding;

    double latitude,longitude;
    WeatherPreference preference = null;
    WeatherApp weatherApp;
    HomeViewModel homeViewModel;

    ForecastAdapter forecastAdapter;

    List list = new ArrayList<>();

    String cityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        init();

        searchBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences("Latitude",0).edit().clear().apply();
                getSharedPreferences("Longitude",0).edit().clear().apply();
                Intent intent = new Intent(
                            SearchActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    finish();

            }
        });
    }

    public void init() {
        gpsTracker = new GPSTracker(this);
        preference = new WeatherPreference(this);
        weatherApp = new WeatherApp();
        cityName = searchBinding.tvEdit.getText().toString();
        preference.setCityName(cityName);
    }

}

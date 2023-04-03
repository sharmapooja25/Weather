package com.example.weatherapp.view;

import android.content.Context;
import android.content.Intent;
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
import com.example.weatherapp.databinding.ActivityHomePageBinding;
import com.example.weatherapp.model.ListModel;
import com.example.weatherapp.model.WeatherList;
import com.example.weatherapp.model.WeatherModel;
import com.example.weatherapp.utlis.WeatherPreference;
import com.example.weatherapp.viewModel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private ActivityHomePageBinding homePageBinding;
    WeatherPreference preference = null;
    WeatherApp weatherApp;
    HomeViewModel homeViewModel;
    ForecastAdapter forecastAdapter;
    List list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePageBinding = DataBindingUtil.setContentView(this, R.layout.activity_home_page);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        preference = new WeatherPreference(this);
        weatherApp = new WeatherApp();

            if(preference.getLongitude() != null) {
                updateLocation();

            }
            else {
                String cityname = preference.getcity();
                updateCityLocation(cityname);
            }



    }

    public void updateLocation() {
        homeViewModel.setlocationCall(preference.getLatitude(),preference.getLongitude(),"f10032af2af94e79378c7a53b6260b6e");
        if(!homeViewModel.getLocationresponse().hasActiveObservers()) {
            homeViewModel.getLocationCall().observe(this, new Observer<WeatherModel>() {
                @Override
                public void onChanged(WeatherModel weatherModel) {

                    if (weatherModel == null) {
                        return;
                    }
                    String tempValue = String.valueOf((int)Math.round(weatherModel.getMain().getTemp()));
                    String FtempValue = String.valueOf((int)Math.round(weatherModel.getMain().getFeelsLike()));
                    homePageBinding.tvLocation.setText(weatherModel.getName());
                    homePageBinding.tvTemp.setText(tempValue +(char) 0x00B0+"C");
                    homePageBinding.tvDes.setText(weatherModel.getWeather().get(0).getDescription());
                    homePageBinding.tvFeels.setText("Feels like "+FtempValue +(char) 0x00B0+"C");
                    homePageBinding.tvWind.setText("Wind: "+weatherModel.getWind().getSpeed().toString()+" Km/h");
                    homePageBinding.tvHumidity.setText("Humidity: "+weatherModel.getMain().getHumidity().toString()+"%");
                    homePageBinding.tvPressure.setText("Pressure: "+weatherModel.getMain().getSeaLevel().toString()+"Pa");
                    homePageBinding.tvVisibility.setText("Visibility: "+weatherModel.getVisibility().toString());
                    Glide.with(HomePageActivity.this)
                            .load(weatherApp.getImageUrl()+weatherModel.getWeather().get(0).
                                    getIcon()+"@2x.png")
                            .into(homePageBinding.tvIcon);

                    homePageBinding.tvLocation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            preference.setLatitude("");
                            preference.setLongitude("");
                            Log.e("preference",preference.getLatitude());
                            Intent intent = new Intent(
                                    HomePageActivity.this, SearchActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });


                }

            });
        }
    }


    private void getForecastDetails() {
        homeViewModel.setForecastCall(preference.getLatitude(),preference.getLongitude(),"f10032af2af94e79378c7a53b6260b6e");
        if (!homeViewModel.getForevastresponse().hasActiveObservers()) {
            homeViewModel.getForecastCall().observe(this, new Observer<WeatherList>() {
                @Override
                public void onChanged(WeatherList response) {

                    if (response == null) {
                        return;
                    }

                    list = response.getList();
                    list.add(list);
                    Log.e("response.getList()",response.getList().toString());


                    forecastAdapter = new ForecastAdapter(list, HomePageActivity.this);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(HomePageActivity.this);
                    homePageBinding.recyForecast.setLayoutManager(layoutManager);
                    layoutManager.setOrientation(RecyclerView.HORIZONTAL);
                    homePageBinding.recyForecast.setItemAnimator(new DefaultItemAnimator());
                    homePageBinding.recyForecast.setAdapter(forecastAdapter);

                }
            });
        }
    }


    public void updateCityLocation(String cityname) {
        homeViewModel.setCityweatherCall(cityname,"f10032af2af94e79378c7a53b6260b6e");
        if(!homeViewModel.getCityforevastresponse().hasActiveObservers()) {
            homeViewModel.getCiryWeatherCall().observe(this, new Observer<WeatherModel>() {
                @Override
                public void onChanged(WeatherModel weatherModel) {

                    if (weatherModel == null) {
                        return;
                    }
                    String tempValue = String.valueOf((int)Math.round(weatherModel.getMain().getTemp()));
                    String FtempValue = String.valueOf((int)Math.round(weatherModel.getMain().getFeelsLike()));
                    homePageBinding.tvLocation.setText(weatherModel.getName());
                    homePageBinding.tvTemp.setText(tempValue +(char) 0x00B0+"C");
                    homePageBinding.tvDes.setText(weatherModel.getWeather().get(0).getDescription());
                    homePageBinding.tvFeels.setText("Feels like "+FtempValue +(char) 0x00B0+"C");
                    homePageBinding.tvWind.setText("Wind: "+weatherModel.getWind().getSpeed().toString()+" Km/h");
                    homePageBinding.tvHumidity.setText("Humidity: "+weatherModel.getMain().getHumidity().toString()+"%");
                    homePageBinding.tvPressure.setText("Pressure: "+weatherModel.getMain().getSeaLevel().toString()+"Pa");
                    homePageBinding.tvVisibility.setText("Visibility: "+weatherModel.getVisibility().toString());
                    Glide.with(HomePageActivity.this)
                            .load(weatherApp.getImageUrl()+weatherModel.getWeather().get(0).
                                    getIcon()+"@2x.png")
                            .into(homePageBinding.tvIcon);


                }

            });
        }
    }

}

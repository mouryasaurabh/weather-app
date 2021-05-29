package com.service.weatherapp.retrofit;

import com.service.weatherapp.models.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitDataService {

    @GET("/data/2.5/weather?q={city_name}&appid=476f95b38ae0635c34193946eee9d39a")
    Call<WeatherModel> getWeatherData(@Query("city_name") String cityName);
}
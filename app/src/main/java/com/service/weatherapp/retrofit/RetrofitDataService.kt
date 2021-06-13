package com.service.weatherapp.retrofit

import com.service.weatherapp.model.WeatherDataEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
/**
 *Retrofit interface for APIs
 */
interface RetrofitDataService {
    @GET("/data/2.5/weather?appid=476f95b38ae0635c34193946eee9d39a")
    fun getWeatherData(@Query("q") cityName: String?): Call<WeatherDataEntity?>?
}
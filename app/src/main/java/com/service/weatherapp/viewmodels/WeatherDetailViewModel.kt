package com.service.weatherapp.viewmodels

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.service.weatherapp.MyApplication
import com.service.weatherapp.model.WeatherDataEntity
import com.service.weatherapp.retrofit.RetrofitClientInstance
import com.service.weatherapp.retrofit.RetrofitDataService
import com.service.weatherapp.room.city.CityEntity
import com.service.weatherapp.room.city.RecentCityRepository
import com.service.weatherapp.room.city.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherDetailViewModel : ViewModel() {

    fun insertCity() {
        GlobalScope.launch(Dispatchers.IO) {
            RecentCityRepository(
                MyApplication.appContext!!
            ).insertCity(
                CityEntity(
                    10,
                    "Delhi"
                )
            )
        }

        GlobalScope.launch(Dispatchers.IO) {
            val cities = RecentCityRepository(
                MyApplication.appContext!!
            ).getAllCities(
            )
            println("xxxxx: City count:${cities.size}")
            for (cityEntity: CityEntity in cities) {
                println("xxxxx: Iterate City:${cityEntity.city_id}, ${cityEntity.city_name}")
            }
        }

        GlobalScope.launch(Dispatchers.IO) {
            val weathers= WeatherRepository(
                    MyApplication.appContext!!
                ).getAllCityWeatherDetails(
                )
            println("xxxxx: Weather count:${weathers.size}")
            for (weather: WeatherDataEntity in weathers) {
                println("xxxxx: Iterate WeatherDataEntity: ${weather.id}, ${weather.name}, ${weather.dt},")
            }
            }
    }

    fun searchCity(cityName: String?) {
        if (cityName.isNullOrEmpty()) {
            Toast.makeText(
                MyApplication.appContext!!, "City Name cannot be empty", Toast.LENGTH_LONG
            ).show()
        }
        val service =
            RetrofitClientInstance.retrofitInstance?.create(
                RetrofitDataService::class.java
            )
        val call = service?.getWeatherData(cityName)
        call?.enqueue(object : Callback<WeatherDataEntity?> {
            override fun onResponse(
                call: Call<WeatherDataEntity?>,
                response: Response<WeatherDataEntity?>
            ) {
                if (response.errorBody() != null) {
                    Toast.makeText(
                        MyApplication.appContext!!,
                        "City not found. Please check city name.",
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
                GlobalScope.launch(Dispatchers.IO) {
                    response.body()?.let {
                        WeatherRepository(
                            MyApplication.appContext!!
                        ).insertWeatherForCity(
                            it
                        )
                    }
                }
                println("onResponse")
            }

            override fun onFailure(
                call: Call<WeatherDataEntity?>,
                t: Throwable
            ) {
                println("onFailure")
            }
        })
    }

}
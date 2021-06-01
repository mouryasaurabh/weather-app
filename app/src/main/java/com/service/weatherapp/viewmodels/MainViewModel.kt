package com.service.weatherapp.viewmodels

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.service.weatherapp.MyApplication
import com.service.weatherapp.model.WeatherModel
import com.service.weatherapp.retrofit.RetrofitClientInstance
import com.service.weatherapp.retrofit.RetrofitDataService
import com.service.weatherapp.room.city.City
import com.service.weatherapp.room.city.RecentCityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    fun insertCity() {
        GlobalScope.launch(Dispatchers.IO) {
            RecentCityRepository(
                MyApplication.appContext!!
            ).insertCity(
                City(
                    1,
                    "Gurgaon"
                )
            )
        }

    }

     fun searchCity(cityName: String?) {
        if (cityName.isNullOrEmpty()) {
            Toast.makeText(
                MyApplication.appContext!!, "City Name cannot be empty", Toast.LENGTH_LONG).show()
        }
        val service =
            RetrofitClientInstance.retrofitInstance?.create(
                RetrofitDataService::class.java
            )
        val call = service?.getWeatherData(cityName)
        call?.enqueue(object : Callback<WeatherModel?> {
            override fun onResponse(
                call: Call<WeatherModel?>,
                response: Response<WeatherModel?>
            ) {
                if (response.errorBody() != null) {
                    Toast.makeText(
                        MyApplication.appContext!!,
                        "City not found. Please check city name.",
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
                println("onResponse")
            }

            override fun onFailure(
                call: Call<WeatherModel?>,
                t: Throwable
            ) {
                println("onFailure")
            }
        })
    }

}
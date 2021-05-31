package com.service.weatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.service.weatherapp.R
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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cityEt: EditText = findViewById(R.id.city_search_et)
        val searchButton: TextView = findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            searchCity(cityEt.text.toString())
        }
        GlobalScope.launch(Dispatchers.IO) {
            RecentCityRepository(
                applicationContext!!
            ).insertCity(
                City(
                    1,
                    "Gurgaon"
                )
            )
        }
    }

    private fun searchCity(cityName: String?) {
        if (cityName.isNullOrEmpty()) {
            Toast.makeText(this, "City Name cannot be empty", Toast.LENGTH_LONG).show()
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
                        this@MainActivity,
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
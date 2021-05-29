package com.service.weatherapp

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.service.weatherapp.models.WeatherModel
import com.service.weatherapp.retrofit.RetrofitClientInstance
import com.service.weatherapp.retrofit.RetrofitDataService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val service =
            RetrofitClientInstance.getRetrofitInstance().create(
                RetrofitDataService::class.java
            )
        val call = service.getWeatherData("London")
        call.enqueue(object : Callback<WeatherModel?> {
            override fun onResponse(
                call: Call<WeatherModel?>,
                response: Response<WeatherModel?>
            ) {
                response.body()
            }

            override fun onFailure(
                call: Call<WeatherModel?>,
                t: Throwable
            ) {
            }
        })

}
}
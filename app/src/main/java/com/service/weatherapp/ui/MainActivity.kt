package com.service.weatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.service.weatherapp.R
import com.service.weatherapp.model.WeatherModel
import com.service.weatherapp.retrofit.RetrofitClientInstance
import com.service.weatherapp.retrofit.RetrofitDataService
import com.service.weatherapp.room.city.City
import com.service.weatherapp.room.city.RecentCityRepository
import com.service.weatherapp.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cityEt: EditText = findViewById(R.id.city_search_et)
        val searchButton: TextView = findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            mainViewModel.searchCity(cityEt.text.toString())
        }
        mainViewModel.insertCity()

    }
}
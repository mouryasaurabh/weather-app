package com.service.weatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import com.service.weatherapp.R
import com.service.weatherapp.viewmodels.MainViewModel

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
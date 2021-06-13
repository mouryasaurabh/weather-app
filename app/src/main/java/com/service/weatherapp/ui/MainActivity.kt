package com.service.weatherapp.ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.service.weatherapp.R
import com.service.weatherapp.room.recentcity.CityEntity
import com.service.weatherapp.ui.fragments.RecentFavouriteFragment
import com.service.weatherapp.ui.fragments.WeatherDetailFragment
import com.service.weatherapp.ui.interfaces.RecentCityItemClickListener
import com.service.weatherapp.ui.viewmodels.MainViewModel

class MainActivity : BaseActivity(), RecentCityItemClickListener {
    private val mainViewModel: MainViewModel by viewModels()
    var cityEt: EditText? = null
    var searchButton: TextView? = null
    var progressFL: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        addClickListeners()
        addObservers()
        addFragment(
            R.id.fragment_container_view,
            RecentFavouriteFragment.newInstance(),
            RecentFavouriteFragment.TAG
        )
    }

    private fun initUI() {
        cityEt = findViewById(R.id.city_search_et)
        searchButton = findViewById(R.id.search_button)
        progressFL = findViewById(R.id.progress_fl)
    }

    private fun addObservers() {
        mainViewModel.getLoaderLiveData().observe(this, Observer {
            if (it) {
                progressFL?.visibility = View.VISIBLE
            } else {
                progressFL?.visibility = View.GONE
            }
        })
        mainViewModel.getWeatherResponseLiveData().observe(this, Observer { weatherData ->
            weatherData?.let {
                replaceFragment(
                    R.id.fragment_container_view,
                    WeatherDetailFragment.newInstance(weatherData.id!!),
                    WeatherDetailFragment.TAG,
                    WeatherDetailFragment.TAG
                )
            }
        })
    }

    private fun addClickListeners() {
        searchButton?.setOnClickListener {
            mainViewModel.searchCity(cityEt?.text.toString())
        }
    }

    override fun onItemClick(cityEntity: CityEntity?) {
        replaceFragment(
            R.id.fragment_container_view,
            WeatherDetailFragment.newInstance(cityEntity?.city_id!!),
            WeatherDetailFragment.TAG,
            WeatherDetailFragment.TAG
        )
    }
}
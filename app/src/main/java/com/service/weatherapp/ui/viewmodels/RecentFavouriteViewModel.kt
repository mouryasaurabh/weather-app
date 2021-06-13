package com.service.weatherapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.service.weatherapp.MyApplication
import com.service.weatherapp.room.recentcity.CityEntity
import com.service.weatherapp.room.recentcity.RecentCityRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecentFavouriteViewModel : ViewModel() {
    private var cityLiveData = MutableLiveData<List<CityEntity>>()
    fun getCityLiveData() = cityLiveData

    fun fetchFavouriteCities() {
        CoroutineScope(Dispatchers.Main).launch {
            cityLiveData.value = RecentCityRepository(
                MyApplication.appContext!!
            ).getAllCities(
            )
        }
    }
}
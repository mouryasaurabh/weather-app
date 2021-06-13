package com.service.weatherapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.service.weatherapp.MyApplication
import com.service.weatherapp.model.WeatherDataEntity
import com.service.weatherapp.room.recentcity.CityEntity
import com.service.weatherapp.room.recentcity.RecentCityRepository
import com.service.weatherapp.room.recentcity.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherDetailViewModel : ViewModel() {

    private val weatherRepo = WeatherRepository(MyApplication.appContext!!)
    private val recentCityRepo = RecentCityRepository(MyApplication.appContext!!)

    private var weatherLiveData = MutableLiveData<WeatherDataEntity>()
    fun getWeatherLiveData() = weatherLiveData
    private var favouriteLiveData = MutableLiveData<Boolean>()
    fun getFavouriteLiveData() = favouriteLiveData

    fun checkIfCityIsFavourite(citId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val count = recentCityRepo.checkIfCityExists(citId)
            if (count >= 1) {
                favouriteLiveData.postValue(true)
            }else{
                favouriteLiveData.postValue(false)
            }
        }
    }

    fun fetchWeatherDetailByCityId(cityid: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            weatherLiveData.postValue(
                weatherRepo.getCityWeatherDetails(
                    cityid
                )
            )
        }
    }

    fun saveRecentCity(cityEntity: CityEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            recentCityRepo.insertCity(cityEntity)
        }
    }

    fun removeRecent(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            recentCityRepo.deleteCity(id)
        }
    }

}
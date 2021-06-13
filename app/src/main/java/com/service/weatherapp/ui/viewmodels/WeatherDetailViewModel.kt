package com.service.weatherapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.service.weatherapp.MyApplication
import com.service.weatherapp.model.WeatherDataEntity
import com.service.weatherapp.room.recentcity.CityEntity
import com.service.weatherapp.room.recentcity.RecentCityRepository
import com.service.weatherapp.room.recentcity.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

       /* GlobalScope.launch(Dispatchers.IO) {
            val cities = RecentCityRepository(
                MyApplication.appContext!!
            ).getAllCities(
            )
            println("xxxxx: City count:${cities.size}")
            for (cityEntity: CityEntity in cities) {
                println("xxxxx: Iterate City:${cityEntity.city_id}, ${cityEntity.city_name}")
            }
        }*/

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

}
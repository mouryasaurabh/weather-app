package com.service.weatherapp.room.recentcity

import android.content.Context
import com.service.weatherapp.model.WeatherDataEntity
import com.service.weatherapp.room.AppDatabase

class WeatherRepository(context: Context) {

    var db: WeatherDao = AppDatabase.getInstance(
        context
    )?.weatherDao()!!

    fun insertWeatherForCity(weatherDataEntity: WeatherDataEntity): Long {
        return db.insert(weatherDataEntity)
    }

    fun deleteCityWeather(cityId: Long) {
        return db.delete(cityId)
    }

    fun getCityWeatherDetails(cityId: Long): WeatherDataEntity {
        return db.getCityWeatherDetails(cityId)
    }

    fun getAllCityWeatherDetails(): List<WeatherDataEntity> {
        return db.getAllCityWeatherDetails()
    }

}
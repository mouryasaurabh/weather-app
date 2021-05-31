package com.service.weatherapp.room.city

import android.content.Context
import com.service.weatherapp.model.WeatherModel
import com.service.weatherapp.room.AppDatabase

class WeatherRepository(context: Context) {

    var db: WeatherDao = AppDatabase.getInstance(
        context
    )?.weatherDao()!!

   /* fun insertWeatherForCity(cityId: Int, weatherModel: WeatherModel) {
        return db.insertWeatherForCity(cityId, weatherModel)
    }

    fun updateCityWeather(cityId: Int, weatherModel: WeatherModel) {
        return db.updateCityWeather(cityId, weatherModel)
    }

    fun deleteCityWeather(cityId: Int) {
        return db.deleteCityWeather(cityId)
    }*/

}
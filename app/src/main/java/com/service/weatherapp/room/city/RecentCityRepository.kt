package com.service.weatherapp.room.city

import android.content.Context
import com.service.weatherapp.room.AppDatabase

class RecentCityRepository(context: Context) {

    var db: CityDao = AppDatabase.getInstance(
        context
    )?.cityDao()!!

    fun getAllRecentCities(): List<City>? {
        return /*db.getAllRecentCities()*/null
    }

    fun insertCity(city: City) {
        db.insert(city.cityId, city.cityName)
    }

    fun deleteCity(city: City) {
        db.delete(city)
    }

}
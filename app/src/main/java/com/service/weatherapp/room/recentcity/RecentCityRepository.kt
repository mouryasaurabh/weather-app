package com.service.weatherapp.room.recentcity

import android.content.Context
import com.service.weatherapp.room.AppDatabase

class RecentCityRepository(context: Context) {

    var db: CityDao = AppDatabase.getInstance(
        context
    )?.cityDao()!!

    fun insertCity(cityEntity: CityEntity): Long {
        return db.insert(cityEntity)
    }

    fun deleteCity(city_id: Int) {
        db.delete(city_id)
    }

    fun getAllCities(): List<CityEntity> {
        return db.getAllCities()
    }

}
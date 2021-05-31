package com.service.weatherapp.room.city

import androidx.room.*
import com.service.weatherapp.room.DataConverter

@Dao
interface CityDao {

//    @Query("SELECT * FROM weather")
//    @TypeConverters(DataConverter::class)
//    fun getAllRecentCities(): List<City>

    @Insert
    fun insert(city: City)

    @Delete
    fun delete(city: City)
}
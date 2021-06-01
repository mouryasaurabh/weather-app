package com.service.weatherapp.room.city

import androidx.room.*
import com.service.weatherapp.room.DataConverter

@Dao
interface CityDao {

//    @Query("SELECT * FROM RecentCity")
//    @TypeConverters(DataConverter::class)
//    fun getAllRecentCities(): List<City>

    @Query("UPDATE RecentCity SET city_id = :id, city_name = :name where city_id = :id")
    fun insert(id: Int, name: String)

    @Delete
    fun delete(city: City)
}
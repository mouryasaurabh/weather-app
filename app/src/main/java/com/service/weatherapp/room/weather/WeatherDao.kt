package com.service.weatherapp.room.recentcity

import androidx.room.*
import com.service.weatherapp.model.WeatherDataEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherDataEntity: WeatherDataEntity): Long

    @Query("DELETE FROM WeatherDetails WHERE id = :city_id")
    fun delete(city_id: Int)

    @Query("SELECT * FROM WeatherDetails WHERE id = :city_id")
    fun getCityWeatherDetails(city_id: Int): WeatherDataEntity

    @Query("SELECT * FROM WeatherDetails")
    fun getAllCityWeatherDetails(): List<WeatherDataEntity>

}
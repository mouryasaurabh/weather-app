package com.service.weatherapp.room.city

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.service.weatherapp.model.WeatherModel

@Entity(tableName = "WeatherDetails")
data class WeatherEntity(
    @PrimaryKey
    @ColumnInfo(name = "city_id") val city_id: Int/*,
    @ColumnInfo(name = "weather_response") val weather: WeatherModel?*/
)
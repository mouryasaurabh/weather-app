package com.service.weatherapp.room.city

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey val city_id: Int,
    @ColumnInfo(name = "city_name") val cityName: String?
)
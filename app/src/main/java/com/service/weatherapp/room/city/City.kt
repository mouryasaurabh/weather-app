package com.service.weatherapp.room.city

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RecentCity")
data class City(
    @PrimaryKey @ColumnInfo(name = "city_id") val cityId: Int,
    @ColumnInfo(name = "city_name") val cityName: String
)
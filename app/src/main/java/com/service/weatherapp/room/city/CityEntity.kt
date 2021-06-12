package com.service.weatherapp.room.city

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RecentCity")
data class CityEntity(
    @PrimaryKey @ColumnInfo(name = "city_id") val city_id: Int,
    @ColumnInfo(name = "city_name") val city_name: String
)
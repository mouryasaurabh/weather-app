package com.service.weatherapp.room.recentcity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "RecentCity")
data class CityEntity(
    @PrimaryKey @ColumnInfo(name = "city_id") val city_id: Long,
    @ColumnInfo(name = "city_name") val city_name: String
) : Serializable
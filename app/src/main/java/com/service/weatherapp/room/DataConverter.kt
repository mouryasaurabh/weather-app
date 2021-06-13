package com.service.weatherapp.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.service.weatherapp.model.WeatherDataEntity
import com.service.weatherapp.model.WeatherItemModel

/**
 *This is a converter class to support serialization dn deserialization in room
 */
class DataConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromWeatherList(value: List<WeatherDataEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<WeatherDataEntity>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toWeatherList(value: String): List<WeatherDataEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<WeatherDataEntity>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromWeatherModelList(value: List<WeatherItemModel>): String {
        val gson = Gson()
        val type = object : TypeToken<List<WeatherItemModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toWeatherModelList(value: String): List<WeatherItemModel> {
        val gson = Gson()
        val type = object : TypeToken<List<WeatherItemModel>>() {}.type
        return gson.fromJson(value, type)
    }
}

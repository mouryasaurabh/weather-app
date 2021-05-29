package com.service.weatherapp.models

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("coord") var coord: CoordItemModel? = null,
    @SerializedName("weather") var weather: List<WeatherItemModel?>? = null,
    @SerializedName("base") var base: String? = null,
    @SerializedName("main") var main: MainItemModel? = null,
    @SerializedName("visibility") var visibility: Int? = null,
    @SerializedName("wind") var wind: WindItemModel? = null,
    @SerializedName("clouds") var clouds: CloudsItemModel? = null,
    @SerializedName("dt") var dt: Long? = null,
    @SerializedName("sys") var sys: SysItemModel? = null,
    @SerializedName("timezone") var timezone: Int? = null,
    @SerializedName("id") var id: Long? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("cod") var cod: Int? = null
)


data class CoordItemModel(
    @SerializedName("lon") var lon: Double? = null,
    @SerializedName("lat") var lat: Double? = null
)

data class WeatherItemModel(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("main") var main: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("icon") var icon: String? = null
)

data class MainItemModel(
    @SerializedName("temp") var temp: Double? = null,
    @SerializedName("feels_like") var feels_like: Double? = null,
    @SerializedName("temp_min") var description: Double? = null,
    @SerializedName("temp_max") var temp_max: Double? = null,
    @SerializedName("pressure") var pressure: Int? = null,
    @SerializedName("humidity") var humidity: Int? = null
)

data class WindItemModel(
    @SerializedName("speed") var speed: Double? = null,
    @SerializedName("deg") var deg: Int? = null
)

data class CloudsItemModel(
    @SerializedName("all") var all: Int? = null
)

data class SysItemModel(
    @SerializedName("type") var type: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("sunrise") var sunrise: Long? = null,
    @SerializedName("sunset") var sunset: Int? = null
)
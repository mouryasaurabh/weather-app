package com.service.weatherapp.model

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.service.weatherapp.room.DataConverter

@Entity(tableName = "WeatherDetails")
data class WeatherDataEntity(
    @Embedded
    @SerializedName("coord") var coord: CoordItemModel? = null,

    @ColumnInfo(name = "weather_list")
    @TypeConverters(DataConverter::class)
    @SerializedName("weather") var weather: List<WeatherItemModel?>? = null,

    @ColumnInfo(name = "base")
    @SerializedName("base") var base: String? = null,

    @Embedded
    @SerializedName("main") var main: MainItemModel? = null,

    @ColumnInfo(name = "visibility")
    @SerializedName("visibility") var visibility: Int? = null,

    @Embedded
    @SerializedName("wind") var wind: WindItemModel? = null,

    @Embedded
    @SerializedName("clouds") var clouds: CloudsItemModel? = null,

    @ColumnInfo(name = "dt")
    @SerializedName("dt") var dt: Long? = null,

    @Embedded
    @SerializedName("sys") var sys: SysItemModel? = null,

    @ColumnInfo(name = "timezone")
    @SerializedName("timezone") var timezone: Int? = null,

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id") var id: Long? = null,

    @ColumnInfo(name = "name")
    @SerializedName("name") var name: String? = null,

    @ColumnInfo(name = "cod")
    @SerializedName("cod") var cod: Int? = null
)

data class CoordItemModel(
    @ColumnInfo(name = "coord_item_lon")
    @SerializedName("lon") var lon: Double? = null,

    @ColumnInfo(name = "coord_item_lat")
    @SerializedName("lat") var lat: Double? = null
)

data class WeatherItemModel(
    @ColumnInfo(name = "weather_item_id")
    @SerializedName("id") var id: Int? = null,

    @ColumnInfo(name = "weather_item_main")
    @SerializedName("main") var main: String? = null,

    @ColumnInfo(name = "weather_item_description")
    @SerializedName("description") var description: String? = null,

    @ColumnInfo(name = "weather_item_icon")
    @SerializedName("icon") var icon: String? = null
)

data class MainItemModel(
    @ColumnInfo(name = "main_item_temp")
    @SerializedName("temp") var temp: Double? = null,

    @ColumnInfo(name = "main_item_feels_like")
    @SerializedName("feels_like") var feels_like: Double? = null,

    @ColumnInfo(name = "main_item_temp_min")
    @SerializedName("temp_min") var description: Double? = null,

    @ColumnInfo(name = "main_item_temp_max")
    @SerializedName("temp_max") var temp_max: Double? = null,

    @ColumnInfo(name = "main_item_pressure")
    @SerializedName("pressure") var pressure: Int? = null,

    @ColumnInfo(name = "main_item_humidity")
    @SerializedName("humidity") var humidity: Int? = null
)

data class WindItemModel(
    @ColumnInfo(name = "wind_item_speed")
    @SerializedName("speed") var speed: Double? = null,

    @ColumnInfo(name = "wind_item_deg")
    @SerializedName("deg") var deg: Int? = null
)

data class CloudsItemModel(
    @ColumnInfo(name = "coulds_item_type")
    @SerializedName("all") var all: Int? = null
)

data class SysItemModel(
    @ColumnInfo(name = "sys_item_type")
    @SerializedName("type") var type: Int? = null,

    @ColumnInfo(name = "sys_item_id")
    @SerializedName("id") var id: Int? = null,

    @ColumnInfo(name = "sys_item_country")
    @SerializedName("country") var country: String? = null,

    @ColumnInfo(name = "sys_item_sunrise")
    @SerializedName("sunrise") var sunrise: Long? = null,

    @ColumnInfo(name = "sys_item_sunset")
    @SerializedName("sunset") var sunset: Int? = null
)
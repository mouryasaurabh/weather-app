package com.service.weatherapp.room

import android.content.Context
import androidx.room.*
import com.service.weatherapp.room.city.City
import com.service.weatherapp.room.city.CityDao
import com.service.weatherapp.room.city.WeatherDao
import com.service.weatherapp.room.city.WeatherEntity

@Database(entities = [City::class, WeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun weatherDao(): WeatherDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "weather.db"
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
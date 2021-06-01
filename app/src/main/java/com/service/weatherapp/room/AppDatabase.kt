package com.service.weatherapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.service.weatherapp.room.city.City
import com.service.weatherapp.room.city.CityDao
import com.service.weatherapp.room.city.WeatherDao
import com.service.weatherapp.room.city.WeatherEntity


@Database(entities = [City::class, WeatherEntity::class], version = 2, exportSchema = false)
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
                    ).addMigrations(MIGRATION_1_2()).allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    class MIGRATION_1_2 : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "CREATE TABLE `RecentCity` (`id` INTEGER, "
                        + "`name` TEXT, PRIMARY KEY(`id`))"
            )
        }
    }
}
package com.service.weatherapp.room.recentcity

import androidx.room.*

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cityEntity: CityEntity): Long

    @Query("DELETE FROM RecentCity WHERE city_id = :city_id")
    fun delete(city_id: Int)

    @Query("SELECT * FROM RecentCity")
    fun getAllCities(): List<CityEntity>

}
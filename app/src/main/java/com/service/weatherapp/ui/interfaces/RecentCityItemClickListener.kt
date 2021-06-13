package com.service.weatherapp.ui.interfaces

import com.service.weatherapp.room.recentcity.CityEntity

interface RecentCityItemClickListener {
    fun onItemClick(cityEntity: CityEntity?)
}
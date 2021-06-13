package com.service.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.service.weatherapp.R
import com.service.weatherapp.model.WeatherDataEntity
import com.service.weatherapp.room.recentcity.CityEntity
import com.service.weatherapp.ui.viewmodels.WeatherDetailViewModel

class WeatherDetailFragment : Fragment() {
    private var cityEntity: CityEntity? = null
    private var weatherDataEntity: WeatherDataEntity? = null

    var weatherDetailViewModel: WeatherDetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherDetailViewModel = ViewModelProvider(this).get(WeatherDetailViewModel::class.java)
        arguments?.let {
            cityEntity = it.get(PARAM_CITY_ENTITY) as? CityEntity?
            weatherDataEntity = it.get(PARAM_WEATHER_ENTITY) as? WeatherDataEntity?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fav = view.findViewById<TextView>(R.id.mark_favourite)
        cityEntity?.let {
            fav.text = requireContext().getString(R.string.delete_favourite)
        } ?: kotlin.run {
            fav.text = requireContext().getString(R.string.save_favourite)
        }
    }

    companion object {
        private val PARAM_CITY_ENTITY: String = "CITY_ENTITY"
        private val PARAM_WEATHER_ENTITY: String = "WEATHER_ENTITY"
        const val TAG: String = "WeatherDetailFragment"


        @JvmStatic
        fun newInstance(
            cityEntity: CityEntity? = null,
            weatherDataEntity: WeatherDataEntity? = null
        ) =
            WeatherDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(PARAM_CITY_ENTITY, cityEntity)
                    putSerializable(PARAM_WEATHER_ENTITY, weatherDataEntity)
                }
            }
    }
}
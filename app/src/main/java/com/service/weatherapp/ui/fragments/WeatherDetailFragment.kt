package com.service.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.service.weatherapp.R
import com.service.weatherapp.model.WeatherDataEntity
import com.service.weatherapp.room.recentcity.CityEntity
import com.service.weatherapp.ui.viewmodels.WeatherDetailViewModel
import com.service.weatherapp.util.AppUtil

class WeatherDetailFragment : Fragment() {
    private var weatherDataEntity: WeatherDataEntity? = null
    private var cityId: Long? = null

    var weatherDetailViewModel: WeatherDetailViewModel? = null

    private var saveTV: TextView? = null
    private var cityDetailsTV: TextView? = null
    private var weatherTV: TextView? = null
    private var temperatureTV: TextView? = null
    private var tempMinTV: TextView? = null
    private var tempMaxTV: TextView? = null
    private var windTV: TextView? = null
    private var pressureTV: TextView? = null
    private var humidityTV: TextView? = null
    private var sunriseTV: TextView? = null
    private var sunsetTV: TextView? = null
    private var lastUpdatedTV: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherDetailViewModel = ViewModelProvider(this).get(WeatherDetailViewModel::class.java)
        arguments?.let {
            cityId = it.getLong(PARAM_CITY_ID)
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
        initUI(view)
        addClickListener()
        addObservers()
    }

    private fun addClickListener() {
        saveTV?.setOnClickListener {
            if (saveTV?.text?.equals(requireContext().getString(R.string.delete_favourite)) == true) {
                //execute delete
                saveTV?.text = requireContext().getString(R.string.save_favourite)
                weatherDetailViewModel?.removeRecent(cityId!!)
            } else {
                //execute save
                saveTV?.text = requireContext().getString(R.string.delete_favourite)
                weatherDetailViewModel?.saveRecentCity(
                    CityEntity(
                        weatherDataEntity?.id!!,
                        weatherDataEntity?.name!!
                    )
                )
            }
        }
    }

    private fun initUI(view: View) {
        saveTV = view.findViewById(R.id.mark_favourite)

        cityDetailsTV = view.findViewById(R.id.city_details)

        weatherTV = view.findViewById(R.id.weather_details)

        temperatureTV = view.findViewById(R.id.temparature_details)
        tempMinTV = view.findViewById(R.id.temparature_min)
        tempMaxTV = view.findViewById(R.id.temparature_max)

        windTV = view.findViewById(R.id.wind_details)
        pressureTV = view.findViewById(R.id.pressure)
        humidityTV = view.findViewById(R.id.humidity)

        sunriseTV = view.findViewById(R.id.sunrise)
        sunsetTV = view.findViewById(R.id.sunset)

        lastUpdatedTV = view.findViewById(R.id.last_updated)
    }

    private fun addObservers() {
        weatherDetailViewModel?.getWeatherLiveData()
            ?.observe(viewLifecycleOwner, Observer { weatherData ->
                weatherData?.let { data ->
                    weatherDataEntity = data
                    updateUI(data)
                }
            })
        weatherDetailViewModel?.getFavouriteLiveData()
            ?.observe(viewLifecycleOwner, Observer {
                if (it == true) {
                    saveTV?.text = requireContext().getString(R.string.delete_favourite)
                } else {
                    saveTV?.text = requireContext().getString(R.string.save_favourite)
                }
            })
    }

    private fun updateUI(data: WeatherDataEntity) {
        cityDetailsTV?.text = String.format(
            getString(R.string.city_details),
            data.name,
            data.sys?.country,
            data.coord?.lat,
            data.coord?.lon
        )

        weatherTV?.text = String.format(
            getString(R.string.weather_details),
            data.weather?.get(0)?.main,
            data.weather?.get(0)?.description
        )
        temperatureTV?.text = String.format(
            getString(R.string.temperature_details),
            data.main?.temp
        )

        tempMinTV?.text = String.format(
            getString(R.string.temperature_min),
            data.main?.temp_min
        )
        tempMaxTV?.text = String.format(
            getString(R.string.temperature_min),
            data.main?.temp_min
        )
        windTV?.text = String.format(
            getString(R.string.wind_details),
            data.wind?.speed,
            data.wind?.deg
        )
        pressureTV?.text = String.format(
            getString(R.string.pressure),
            data.main?.pressure
        )
        humidityTV?.text = String.format(
            getString(R.string.pressure),
            data.main?.humidity
        )
        sunriseTV?.text = String.format(
            getString(R.string.sunrise),
            AppUtil.getFormattedDate(data.sys?.sunrise!!)
        )
        sunsetTV?.text = String.format(
            getString(R.string.sunset),
            AppUtil.getFormattedDate(data.sys?.sunset!!)
        )
        lastUpdatedTV?.text = String.format(
            getString(R.string.last_updated),
            AppUtil.getFormattedDate(data.dt!!)
        )
    }

    override fun onResume() {
        super.onResume()
        weatherDetailViewModel?.fetchWeatherDetailByCityId(
            cityId!!
        )
        weatherDetailViewModel?.checkIfCityIsFavourite(cityId!!)
    }

    companion object {
        private val PARAM_CITY_ID: String = "CITY_ID"
        const val TAG: String = "WeatherDetailFragment"

        @JvmStatic
        fun newInstance(
            cityId: Long
        ) =
            WeatherDetailFragment().apply {
                arguments = Bundle().apply {
                    putLong(PARAM_CITY_ID, cityId)
                }
            }
    }
}

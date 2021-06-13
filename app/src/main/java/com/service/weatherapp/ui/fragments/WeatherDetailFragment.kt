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
import org.w3c.dom.Text

class WeatherDetailFragment : Fragment() {
    private var weatherDataEntity: WeatherDataEntity? = null
    private var cityId: Long? = null

    var weatherDetailViewModel: WeatherDetailViewModel? = null

    private var cityName: TextView? = null
    private var saveTV: TextView? = null

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
        cityName = view.findViewById(R.id.city_details)
    }

    private fun addObservers() {
        weatherDetailViewModel?.getWeatherLiveData()
            ?.observe(viewLifecycleOwner, Observer { weatherData ->
                weatherData?.let { data ->
                    weatherDataEntity = data
                    cityName?.text = data.name
                }
            })
        weatherDetailViewModel?.getFavouriteLiveData()?.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                saveTV?.text = requireContext().getString(R.string.delete_favourite)
            } else {
                saveTV?.text = requireContext().getString(R.string.save_favourite)
            }
        })
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
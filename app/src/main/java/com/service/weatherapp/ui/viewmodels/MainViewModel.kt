package com.service.weatherapp.ui.viewmodels

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.service.weatherapp.MyApplication
import com.service.weatherapp.R
import com.service.weatherapp.model.WeatherDataEntity
import com.service.weatherapp.retrofit.RetrofitClientInstance
import com.service.weatherapp.retrofit.RetrofitDataService
import com.service.weatherapp.util.AppUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    var appContext = MyApplication.appContext!!

    val service =
        RetrofitClientInstance.retrofitInstance?.create(
            RetrofitDataService::class.java
        )

    private val loaderLiveData = MutableLiveData<Boolean>()
    private val weatherResponseLiveData = MutableLiveData<WeatherDataEntity>()
    fun getLoaderLiveData() = loaderLiveData
    fun getWeatherResponseLiveData() = weatherResponseLiveData

    fun searchCity(cityName: String?) {
        if (cityName.isNullOrEmpty()) {
            Toast.makeText(
                appContext, appContext.getString(R.string.empty_city_name), Toast.LENGTH_LONG
            ).show()
        }

        if (!AppUtil.isNetworkConnected()) {
            Toast.makeText(
                appContext, appContext.getString(R.string.no_internet), Toast.LENGTH_LONG
            ).show()
            return
        }

        loaderLiveData.value = true

        val call = service?.getWeatherData(cityName)
        call?.enqueue(object : Callback<WeatherDataEntity?> {
            override fun onResponse(
                call: Call<WeatherDataEntity?>,
                response: Response<WeatherDataEntity?>
            ) {
                loaderLiveData.value = false
                handleResponse(response)
            }

            override fun onFailure(
                call: Call<WeatherDataEntity?>,
                t: Throwable
            ) {
                loaderLiveData.value = false
                Toast.makeText(
                    appContext,
                    appContext.getString(R.string.something_went_wrong),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun handleResponse(response: Response<WeatherDataEntity?>) {
        if (response.errorBody() != null) {
            Toast.makeText(
                appContext, appContext.getString(R.string.city_not_found), Toast.LENGTH_LONG
            ).show()
            return
        }
        weatherResponseLiveData.value = response.body()
    }
}
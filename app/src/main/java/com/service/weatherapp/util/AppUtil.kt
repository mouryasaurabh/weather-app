package com.service.weatherapp.util

import android.content.Context
import android.net.ConnectivityManager
import com.service.weatherapp.MyApplication
/**
*Util class for app level uitility functions
*/
object AppUtil {
    /**
     *This method checks if user is connected to the internet
     */
    fun isNetworkConnected(): Boolean {
        val cm =
            MyApplication.appContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo?.isConnected == true
    }

}

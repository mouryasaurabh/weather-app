package com.service.weatherapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.service.weatherapp.MyApplication
import java.text.SimpleDateFormat
import java.util.*

/**
 *Util class for app level uitility functions
 */
object AppUtil {
    const val DATE_FORMAT = "dd/MM/yyyy hh:mm a"

    /**
     *This method checks if user is connected to the internet
     */
    fun isNetworkConnected(): Boolean {
        val cm =
            MyApplication.appContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo?.isConnected == true
    }

    /**
     *Convert timestamp to date
     */
    fun getFormattedDate(time: Long): String {
        val cal = Calendar.getInstance()
        val tz = cal.getTimeZone()
        val sdf = SimpleDateFormat(DATE_FORMAT)
        sdf.setTimeZone(tz);//set time zone.
        val localTime = sdf.format(Date(time * 1000))
        var date = Date()
        try {
            date = sdf.parse(localTime)
        } catch (e: Exception) {
            Log.e("AppUtil", e.localizedMessage)
        }
        return date.toString()
    }


}

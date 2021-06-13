package com.service.weatherapp

import android.app.Application
import android.content.Context
/**
 *This is application class for project
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        var appContext: Context? = null
    }
}
package com.service.weatherapp.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *This is a singleton class to fetch retorfit instance that can be used across project
 */
object RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://api.openweathermap.org"
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level=HttpLoggingInterceptor.Level.BODY
                val client =
                    OkHttpClient.Builder().addInterceptor(interceptor).build()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
            return retrofit
        }
}
package com.kartikaymahajan.weatherapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants {

    const val APP_ID:String="bc1a3ee99d5afbe0e5a27aff7086a94d"
    const val BASE_URL: String="http://api.openweathermap.org/data/"
    const val METRIC_UNIT:String="metric"
    const val PREFERENCE_NAME="WeatherAppPreference"
    const val WEATHER_RESPONSE_DATA="weather_response_data"


    fun isNetworkAvailable(context: Context):Boolean {
        val connectivityManager = context.
                getSystemService(Context.CONNECTIVITY_SERVICE) as
                    ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network=
                connectivityManager.activeNetwork?:return false
            val activeNetwork=connectivityManager.
                getNetworkCapabilities(network)?:return false

            return when{
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
                else->false
            }

        } else {

            val netWorkInfo = connectivityManager.activeNetworkInfo
            return netWorkInfo != null && netWorkInfo.isConnectedOrConnecting

        }
    }
}
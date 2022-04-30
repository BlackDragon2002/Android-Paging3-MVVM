package com.example.aniga.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities
import android.os.Build

fun checkForInternetConnection(context: Context):Boolean{
    val connectivityManager=
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities=connectivityManager.activeNetwork?: return false
        val capabilities=
            connectivityManager.getNetworkCapabilities(networkCapabilities)?:return false
        return when{
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
            else->false
        }
    }else{
        connectivityManager.activeNetworkInfo?.run {
            return when(type){
                TYPE_WIFI->true
                TYPE_MOBILE->true
                TYPE_ETHERNET->true
                else ->false
            }
        }
    }
    return false
}
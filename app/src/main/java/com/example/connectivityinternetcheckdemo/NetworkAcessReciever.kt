package com.example.connectivityinternetcheckdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import java.io.IOException
import java.lang.NullPointerException

class NetworkAcessReciever : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
try {


    if (!isOnline(context)) {

        context.startActivity(Intent(context.applicationContext, NoInternet::class.java))

    }
}
        catch (e:NullPointerException){
            e.printStackTrace()
        }

    }

    fun isOnline(context: Context):Boolean{

        try {
            val connectivityManager:ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return (networkInfo!= null && networkInfo.isConnected)
        }
        catch (e: NullPointerException){
            e.printStackTrace()
        }
        return false
    }
}
package com.example.connectivityinternetcheckdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
class ConnectivityReciever: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if(connectivityReceiverListener!=null){
            connectivityReceiverListener!!.onNetworkConnectionChanged(isConnectingOrConnected(context!!))
        }
    }

    fun isConnectingOrConnected(context: Context):Boolean{

            val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return (networkInfo!= null && networkInfo.isConnected)
    }

    interface ConnectivityRecieverListener{
        fun onNetworkConnectionChanged(isConnected:Boolean)
    }

    companion object {
        var connectivityReceiverListener: ConnectivityRecieverListener? = null
    }
}
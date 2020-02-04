package com.example.connectivityinternetcheckdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NoInternet : AppCompatActivity() ,ConnectivityReciever.ConnectivityRecieverListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)

//        onNetworkConnectionChanged(false)

        if (!Utillls.isNetworkAvailable(this)){

        }
        else
        {
            val intent = Intent()
            setResult(2, intent)
            finish()
        }


    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if(isConnected)
            finish()

    }

    override fun onResume() {
        super.onResume()
    }
}

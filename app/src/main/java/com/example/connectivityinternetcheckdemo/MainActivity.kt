package com.example.connectivityinternetcheckdemo

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), ConnectivityReciever.ConnectivityRecieverListener {


    var context: Context? = this
    //private var linear: ConstraintLayout? = null
    //private var snackbar: Snackbar? = null
    private var connectivityReceiver: ConnectivityReciever? = null
    var called:Boolean = true


    var callback: CallbackInternet? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val networkAcessReciever = NetworkAcessReciever()
        networkAcessReciever.onReceive(this@MainActivity,getIntent())
        registerReceiver(ConnectivityReciever(),IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

    }

    override fun onPause() {
        super.onPause()
        ConnectivityReciever.connectivityReceiverListener = this
        if (connectivityReceiver != null) {
            unregisterReceiver(connectivityReceiver)
        }
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReciever.connectivityReceiverListener = this
        registerReceiver(ConnectivityReciever(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onDestroy() {
        super.onDestroy()
        context = null
        //swipeRefreshLayout = null

        ConnectivityReciever.connectivityReceiverListener = this
        unregisterReceiver(connectivityReceiver)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        Toast.makeText(this,isConnected.toString(),Toast.LENGTH_SHORT).show()
            showMessage(isConnected)

    }

    private fun showMessage(isConnected: Boolean) {

        if (!isConnected) {
            if(called){
                called=false

                startActivityForResult(Intent(this, NoInternet::class.java), 2)

            }
        } else {
            // Toast.makeText(this, "is connected", Toast.LENGTH_SHORT).show()
            callback?.netListner()

            // OrderdetailSingleton.getInstance().setNetBool(isConnected)
            //finish();
        }

    }
}

interface CallbackInternet {
    fun netListner()

}


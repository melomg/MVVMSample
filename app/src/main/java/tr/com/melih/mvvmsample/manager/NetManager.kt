package tr.com.melih.mvvmsample.manager

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by melih on 24/09/2017.
 */
class NetManager(private var applicationContext: Context) {
    val isConnectedToInternet: Boolean?
        get() {
            val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }
}
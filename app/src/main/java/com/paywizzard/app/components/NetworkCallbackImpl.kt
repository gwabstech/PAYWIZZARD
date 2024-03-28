package com.paywizzard.app.components

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest

class NetworkCallbackImpl : ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: android.net.Network) {
        super.onAvailable(network)
        // Device connected to a network with internet capabilities
        // Perform actions that require internet access
    }

    override fun onLost(network: android.net.Network) {
        super.onLost(network)
        // Device lost connection to the network
        // Handle offline behavior
    }
}

fun registerNetworkCallback(context: Context, networkCallback: NetworkCallbackImpl) {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    val networkRequest = NetworkRequest.Builder().build()
    connectivityManager?.registerNetworkCallback(networkRequest, networkCallback)
}

fun unregisterNetworkCallback(context: Context, callback: ConnectivityManager.NetworkCallback) {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.unregisterNetworkCallback(callback)
}

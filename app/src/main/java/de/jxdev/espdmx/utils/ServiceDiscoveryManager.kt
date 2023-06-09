package de.jxdev.espdmx.utils

import android.content.Context
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log
import androidx.lifecycle.MutableLiveData
import de.jxdev.espdmx.model.DiscoveredDevice
import java.net.InetAddress

class ServiceDiscoveryManager (context: Context) {
    private val LOGTAG = "DiscoveryManager"
    private val SERVICETYPE = "_espdmx._tcp"
    private val nsdManager = context.getSystemService(Context.NSD_SERVICE) as NsdManager
    private val serviceList: MutableList<DiscoveredDevice> = mutableListOf()

    public val liveData = MutableLiveData<MutableList<DiscoveredDevice>>()

    private fun addToServiceList(discoveredDevice: DiscoveredDevice) {
        serviceList.add(discoveredDevice)
        liveData.postValue(serviceList)
    }
    private fun removeFromServiceList(discoveredDevice: DiscoveredDevice) {
        serviceList.remove(discoveredDevice)
        liveData.postValue(serviceList)
    }

    private val resolveListener = object : NsdManager.ResolveListener {

        override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
            // Called when the resolve fails. Use the error code to debug.
            Log.e(LOGTAG, "Resolve failed: $errorCode")
        }

        override fun onServiceResolved(serviceInfo: NsdServiceInfo) {
            Log.e(LOGTAG, "Resolve Succeeded. $serviceInfo")

            /*
            if (serviceInfo.serviceName == mServiceName) {
                Log.d(LOGTAG, "Same IP.")
                return
            }*/
            val port: Int = serviceInfo.port
            val host: InetAddress = serviceInfo.host
        }
    }

    public val discoveryListener = object : NsdManager.DiscoveryListener {

        // Called as soon as service discovery begins.
        override fun onDiscoveryStarted(regType: String) {
            Log.d(LOGTAG, "Service discovery started")
        }

        override fun onServiceFound(service: NsdServiceInfo) {
            // A service was found! Do something with it.
            Log.d(LOGTAG, "Service discovery success$service")
            /*
            when {
                service.serviceType != SERVICE_TYPE -> // Service type is the string containing the protocol and
                    // transport layer for this service.
                    Log.d(TAG, "Unknown Service Type: ${service.serviceType}")
                service.serviceName == mServiceName -> // The name of the service tells the user what they'd be
                    // connecting to. It could be "Bob's Chat App".
                    Log.d(TAG, "Same machine: $mServiceName")
                service.serviceName.contains("NsdChat") -> nsdManager.resolveService(service, resolveListener)
            }*/
            nsdManager.resolveService(service,resolveListener)
        }

        override fun onServiceLost(service: NsdServiceInfo) {
            // When the network service is no longer available.
            // Internal bookkeeping code goes here.
            Log.e(LOGTAG, "service lost: $service")
        }

        override fun onDiscoveryStopped(serviceType: String) {
            Log.i(LOGTAG, "Discovery stopped: $serviceType")
        }

        override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
            Log.e(LOGTAG, "Discovery failed: Error code:$errorCode")
            nsdManager.stopServiceDiscovery(this)
        }

        override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
            Log.e(LOGTAG, "Discovery failed: Error code:$errorCode")
            nsdManager.stopServiceDiscovery(this)
        }
    }

    public fun startDiscovery() {
        nsdManager.discoverServices(SERVICETYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener)
    }
    public fun stopDiscovery() {
        nsdManager.stopServiceDiscovery(discoveryListener)
    }
}
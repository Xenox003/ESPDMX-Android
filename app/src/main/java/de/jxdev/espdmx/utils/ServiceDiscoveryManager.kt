package de.jxdev.espdmx.utils

import android.content.Context
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log
import androidx.lifecycle.MutableLiveData
import de.jxdev.espdmx.model.DiscoveredDevice
import java.lang.Exception
import java.net.InetAddress

class ServiceDiscoveryManager (context: Context) {
    private val LOGTAG = "DiscoveryManager"
    private val SERVICETYPE = "_espdmx._tcp"
    private val nsdManager = context.getSystemService(Context.NSD_SERVICE) as NsdManager
    private val serviceList: MutableList<DiscoveredDevice> = mutableListOf()
    private var discoveryRunning = false

    public val liveData = MutableLiveData<MutableList<DiscoveredDevice>>()

    private fun addToServiceList(discoveredDevice: DiscoveredDevice) {
        serviceList.add(discoveredDevice)
        updateServiceListLive()
    }
    private fun removeFromServiceList(discoveredDevice: DiscoveredDevice) {
        serviceList.remove(discoveredDevice)
        updateServiceListLive()
    }
    private fun updateServiceListLive() {
        liveData.postValue(serviceList)
    }

    public val discoveryListener = object : NsdManager.DiscoveryListener {

        // Called as soon as service discovery begins.
        override fun onDiscoveryStarted(regType: String) {
            Log.d(LOGTAG, "Service discovery started")
        }

        override fun onServiceFound(service: NsdServiceInfo) {
            // A service was found! Do something with it.
            Log.d(LOGTAG, "Service discovery success$service")

            val resolveListener = object : NsdManager.ResolveListener {

                override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
                    // Called when the resolve fails. Use the error code to debug.
                    Log.e(LOGTAG, "Resolve failed: $errorCode")
                }

                override fun onServiceResolved(serviceInfo: NsdServiceInfo) {
                    Log.d(LOGTAG, "Resolve Succeeded. $serviceInfo")

                    if (serviceInfo.host.isReachable(2000)) {
                        addToServiceList(
                            DiscoveredDevice(
                                name = serviceInfo.serviceName,
                                type = serviceInfo.serviceType,
                                host = serviceInfo.host,
                                port = serviceInfo.port
                            )
                        )
                    }
                }
            }

            nsdManager.resolveService(service,resolveListener)
        }

        override fun onServiceLost(service: NsdServiceInfo) {
            // When the network service is no longer available.
            // Internal bookkeeping code goes here.
            Log.e(LOGTAG, "service lost: $service")
        }

        override fun onDiscoveryStopped(serviceType: String) {
            Log.i(LOGTAG, "Discovery stopped: $serviceType")
            startDiscovery()
        }

        override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
            Log.e(LOGTAG, "Discovery failed: Error code:$errorCode")
            try {
                nsdManager.stopServiceDiscovery(this)
            } catch (e: Exception) {
                Log.e(LOGTAG, "Failed to Stop DiscoveryListener: ${e.message}")
            }

        }

        override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
            Log.e(LOGTAG, "Discovery failed: Error code:$errorCode")
            try {
                nsdManager.stopServiceDiscovery(this)
            } catch (e: Exception) {
                Log.e(LOGTAG, "Failed to Stop DiscoveryListener: ${e.message}")
            }
        }
    }

    public fun startDiscovery() {
        serviceList.clear()
        updateServiceListLive()
        nsdManager.discoverServices(SERVICETYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener)
    }
    public fun stopDiscovery() {
        try {
            nsdManager.stopServiceDiscovery(discoveryListener)
        } catch (e: Exception) {
            Log.e(LOGTAG, "Failed to Stop DiscoveryListener: ${e.message}")
        }
    }

    init {
        this.startDiscovery()
    }
}
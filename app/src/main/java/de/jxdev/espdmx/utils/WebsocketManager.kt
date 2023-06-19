package de.jxdev.espdmx.utils

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.net.InetAddress
import java.util.Timer
import java.util.TimerTask

class WebsocketManager (context : Context) {
    private val logTag = "WebsocketListener"

    private val client = OkHttpClient()
    private var socketAddress : InetAddress? = null
    private var socketUrl : String? = null
    val socketListener = WebSocketListener(this)
    var socket : WebSocket? = null
    fun setAddress(address: InetAddress) {
        socketAddress = address
        socketUrl = "ws://${socketAddress?.hostAddress.toString()}/ws"
    }

    fun connect() {
        if (socketUrl == null) {
            Log.e(logTag,"Cannot Connect to WebSocket, address not set!")
            return
        }
        /*
        if (socketListener.getIsConnected()) {
            Log.e(logTag, "Cannot Connect to WebSocket, already connected!")
            return
        }
        */

        socket?.close(1000, "Disconnect")
        socket = client.newWebSocket(Request.Builder().url(socketUrl!!).build(),socketListener)
    }

    fun disconnect() {
        if (socket == null) {
            Log.e(logTag, "Cannot disconnect from Socket, socket is not connected!")
            return
        }

        socket?.close(1000,"Disconnect")
    }
}

class WebSocketListener (private val socketManager: WebsocketManager) : WebSocketListener(){
    private val logTag = "WebsocketListener"
    var isConnectedLive = MutableLiveData<Boolean>(false)
    var isAlive = false
    var lastAliveTick : Long = 0

    private fun setIsConnected(isConnected : Boolean) {
        isConnectedLive.postValue(isConnected)
    }
    fun getIsConnected() : Boolean {
        return isConnectedLive.value ?: false
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        Log.d(logTag, "onOpen:")

        setIsConnected(true)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        Log.d(logTag, "onMessage: $text")

        lastAliveTick = System.currentTimeMillis()
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        Log.d(logTag, "onClosing: $code $reason")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        Log.d(logTag, "onClosed: $code $reason")


        setIsConnected(false)

        // Attempt reconnect when error
        if (code != 1000 && reason != "Disconnect") {
            Log.e(logTag, "Websocket Connect closed unexpectedly, reconnecting...")
            socketManager.connect()
        }

    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Log.e(logTag, "onFailure: ${t.message} $response")
        super.onFailure(webSocket, t, response)


        setIsConnected(false)

        // Attempt reconnect when error
        Log.e(logTag, "Websocket FAIL ${t.message} $response, reconnecting...")
        socketManager.connect()

    }

    private fun updateAliveStatus() {
        isAlive = System.currentTimeMillis() - lastAliveTick < 2000
    }

    init {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val oldAliveStatus = isAlive
                updateAliveStatus()

                if (oldAliveStatus != isAlive) {
                    Log.d(logTag,"Alive Status changed to $isAlive")

                    if (!isAlive) {
                        Log.e(logTag, "Websocket ALIVE signal failure, Attempting Reconnect...")
                        setIsConnected(false)
                        socketManager.connect()
                    }
                }
            }
        },0,200)
    }
}
package de.jxdev.espdmx.utils

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.time.LocalDateTime

class SocketLog {
    private val logList : MutableList<SocketLogEntry> = mutableListOf()
    val liveLog: LiveData<MutableList<SocketLogEntry>> = MutableLiveData<MutableList<SocketLogEntry>>()

    fun log (msg : String) {
        logList.add(SocketLogEntry(msg))
        liveLog.setValue(logList)
        Log.d("Te",liveLog.toString())
    }

}

class SocketLogEntry (msg : String, timestamp: LocalDateTime = LocalDateTime.now()) {
    val msg = msg
    val timestamp = timestamp
}
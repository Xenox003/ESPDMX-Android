package de.jxdev.espdmx.utils

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.time.LocalDateTime

class SocketLog {
    val logList = mutableStateListOf<SocketLogEntry>()

    fun log (msg : String) {
        logList.add(SocketLogEntry(msg))
    }

}

class SocketLogEntry (msg : String, timestamp: LocalDateTime = LocalDateTime.now()) {
    val msg = msg
    val timestamp = timestamp
}
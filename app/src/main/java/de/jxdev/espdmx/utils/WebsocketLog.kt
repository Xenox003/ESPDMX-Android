package de.jxdev.espdmx.utils

import androidx.compose.runtime.mutableStateListOf
import java.time.LocalDateTime

class WebsocketLog {
    val logList = mutableStateListOf<SocketLogEntry>()

    fun log (msg : String) {
        logList.add(SocketLogEntry(msg))
    }

}

class SocketLogEntry (msg : String, timestamp: LocalDateTime = LocalDateTime.now()) {
    val msg = msg
    val timestamp = timestamp
}
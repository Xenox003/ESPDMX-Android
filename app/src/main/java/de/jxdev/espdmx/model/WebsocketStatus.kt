package de.jxdev.espdmx.model

data class WebsocketStatus (
    var isConnected : Boolean = false,
    var isAlive : Boolean = false,
    var lastAliveTick : Long = 0
)
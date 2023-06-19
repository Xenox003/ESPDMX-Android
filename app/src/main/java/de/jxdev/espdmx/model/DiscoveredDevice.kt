package de.jxdev.espdmx.model

import java.net.InetAddress

data class DiscoveredDevice (
    var name : String? = null,
    var type : String? = null,
    var host : InetAddress? = null,
    var port : Int? = null
)
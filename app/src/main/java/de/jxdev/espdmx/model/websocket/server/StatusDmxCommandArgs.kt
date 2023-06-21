package de.jxdev.espdmx.model.websocket.server

import com.google.gson.annotations.SerializedName
import de.jxdev.espdmx.model.websocket.CommandArgs

data class StatusDmxCommandArgs (
    @SerializedName("list")     val list : ArrayList<Int>   = arrayListOf()
) : CommandArgs()
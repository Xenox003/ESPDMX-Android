package de.jxdev.espdmx.model.websocket.client

import com.google.gson.annotations.SerializedName
import de.jxdev.espdmx.model.websocket.CommandArgs

data class CreateGroupCommandArgs(
    @SerializedName("name")     val name    : String?               = null,
    @SerializedName("patches")  val patches : ArrayList<String>     = arrayListOf()
) : CommandArgs()
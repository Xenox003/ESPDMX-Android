package de.jxdev.espdmx.model.websocket.client

import com.google.gson.annotations.SerializedName
import de.jxdev.espdmx.model.websocket.CommandArg

data class CreateGroupCommandArgs(
    @SerializedName("name")     val name    : String?               = null,
    @SerializedName("patches")  val patches : ArrayList<String>     = arrayListOf()
) : CommandArg()
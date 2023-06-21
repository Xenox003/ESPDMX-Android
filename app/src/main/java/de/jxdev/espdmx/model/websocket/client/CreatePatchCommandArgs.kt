package de.jxdev.espdmx.model.websocket.client

import com.google.gson.annotations.SerializedName
import de.jxdev.espdmx.model.websocket.CommandArg

data class CreatePatchCommandArgs (
    @SerializedName("name")         val name            : String?   = null,
    @SerializedName("fixtureName")  val fixtureName     : String?   = null,
    @SerializedName("startChannel") val startChannel    : Int?      = null
) : CommandArg()
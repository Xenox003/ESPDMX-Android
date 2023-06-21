package de.jxdev.espdmx.model.websocket

import com.google.gson.annotations.SerializedName
import de.jxdev.espdmx.model.websocket.client.CreateFixtureCommandArgs

data class Command (
    @SerializedName("base_command")     val baseCommand : String? = null,
    @SerializedName("sub_command")      val subCommand : String? = null,
    @SerializedName("args")             val args: CommandArg? = null,
)
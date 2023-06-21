package de.jxdev.espdmx.model.websocket

import com.google.gson.annotations.SerializedName

data class Command (
    @SerializedName("base_command")     val baseCommand : String? = null,
    @SerializedName("sub_command")      val subCommand : String? = null,
    @SerializedName("args")             val args: CommandArgs? = null,
)
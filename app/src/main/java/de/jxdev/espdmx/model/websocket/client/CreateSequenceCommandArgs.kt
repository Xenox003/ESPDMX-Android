package de.jxdev.espdmx.model.websocket.client

import com.google.gson.annotations.SerializedName
import de.jxdev.espdmx.model.websocket.CommandArgs

data class CreateSequenceCommandArgs (
    @SerializedName("name")     val name    : String?           = null,
    @SerializedName("cues")     val cues    : ArrayList<Cue>    = arrayListOf()
) : CommandArgs()
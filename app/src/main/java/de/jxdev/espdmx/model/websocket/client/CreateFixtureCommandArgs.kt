package de.jxdev.espdmx.model.websocket.client

import com.google.gson.annotations.SerializedName
import de.jxdev.espdmx.model.websocket.CommandArgs
import de.jxdev.espdmx.model.websocket.client.enums.ChannelType

data class CreateFixtureCommandArgs (
    @SerializedName("name"         ) var name         : String?           = null,
    @SerializedName("channelCount" ) var channelCount : Int?              = null,
    @SerializedName("channelNames" ) var channelNames : ArrayList<String> = arrayListOf(),
    @SerializedName("channelTypes" ) var channelTypes : ArrayList<ChannelType> = arrayListOf()
) : CommandArgs()
package de.jxdev.espdmx.model.websocket.client

import com.google.gson.annotations.SerializedName
import de.jxdev.espdmx.model.websocket.CommandArg

data class CreateFixtureCommandArgs (
    @SerializedName("name"         ) var name         : String?           = null,
    @SerializedName("channelCount" ) var channelCount : Int?              = null,
    @SerializedName("channelNames" ) var channelNames : ArrayList<String> = arrayListOf(),
    @SerializedName("channelTypes" ) var channelTypes : ArrayList<ChannelType> = arrayListOf()
) : CommandArg()

enum class ChannelType(val value : String) {
    @SerializedName("BARE")
    BARE("BARE"),

    @SerializedName("COLOR_R")
    COLOR_R("COLOR_R"),

    @SerializedName("COLOR_G")
    COLOR_G("COLOR_G"),

    @SerializedName("COLOR_B")
    COLOR_B("COLOR_B")
}
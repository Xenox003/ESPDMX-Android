package de.jxdev.espdmx.model.websocket.client.enums

import com.google.gson.annotations.SerializedName

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
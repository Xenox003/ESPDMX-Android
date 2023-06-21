package de.jxdev.espdmx.model.websocket.client.enums

import com.google.gson.annotations.SerializedName

enum class CueType(val value : String) {
    @SerializedName("COLOR")
    COLOR("COLOR"),

    @SerializedName("CH")
    CH("COLOR")
}
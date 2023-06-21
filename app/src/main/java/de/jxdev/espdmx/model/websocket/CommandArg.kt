package de.jxdev.espdmx.model.websocket

import de.jxdev.espdmx.model.websocket.client.CreateFixtureCommandArgs

open class CommandArg {}

enum class CommandType(
    val baseCommand: String,
    val subCommand: String,
    val argBody : Class<*>,
) {
    CREATE_FIXTURE("CREATE","FIXTURE", CreateFixtureCommandArgs::class.java)
}
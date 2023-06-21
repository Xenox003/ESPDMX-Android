package de.jxdev.espdmx.model.websocket

import de.jxdev.espdmx.model.websocket.client.CreateFixtureCommandArgs
import de.jxdev.espdmx.model.websocket.client.CreateGroupCommandArgs
import de.jxdev.espdmx.model.websocket.client.CreatePatchCommandArgs
import de.jxdev.espdmx.model.websocket.client.CreateSequenceCommandArgs
import de.jxdev.espdmx.model.websocket.server.StatusDmxCommandArgs

open class CommandArgs {} // Just a top-type for the CommandArgs classes

enum class CommandType(
    val baseCommand: String,
    val subCommand: String,
    val argBody : Class<*>,
) {
    // Client Commands
    CREATE_FIXTURE("CREATE","FIXTURE", CreateFixtureCommandArgs::class.java),
    CREATE_GROUP("CREATE","GROUP",CreateGroupCommandArgs::class.java),
    CREATE_PATCH("CREATE","PATCH",CreatePatchCommandArgs::class.java),
    CREATE_SEQUENCE("CREATE","SEQUENCE",CreateSequenceCommandArgs::class.java),

    // Server Commands
    STATUS_DMX("STATUS","DMX",StatusDmxCommandArgs::class.java)
}
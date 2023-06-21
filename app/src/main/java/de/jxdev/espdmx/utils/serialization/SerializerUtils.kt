package de.jxdev.espdmx.utils.serialization

import de.jxdev.espdmx.model.websocket.CommandType

fun getArgClass(baseCommand : String, subCommand : String) : CommandType? {
    return CommandType.values().firstOrNull() {
        it.baseCommand == baseCommand && it.subCommand == subCommand
    }
}
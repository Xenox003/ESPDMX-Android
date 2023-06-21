package de.jxdev.espdmx.utils.serialization

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import de.jxdev.espdmx.model.websocket.Command
import de.jxdev.espdmx.model.websocket.CommandType
import de.jxdev.espdmx.model.websocket.CommandArgs
import java.lang.reflect.Type

class CommandDeserializer : JsonDeserializer<Command> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Command {
        val args = json?.asJsonObject?.get("args")
        val baseCommand = json?.asJsonObject?.get("base_command")?.asString
        val subCommand = json?.asJsonObject?.get("sub_command")?.asString

        if (args == null || baseCommand == null || subCommand == null) return Command()

        val argClassType = getArgClass(baseCommand.toString(), subCommand.toString())
        val argBody = context?.deserialize<CommandArgs>(args, argClassType?.argBody ?: CommandArgs::class.java)

        return Command(
            baseCommand = baseCommand.toString(),
            subCommand = subCommand.toString(),
            args = argBody
        )

    }
}
package de.jxdev.espdmx

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import de.jxdev.espdmx.model.websocket.Command
import de.jxdev.espdmx.model.websocket.client.CreateFixtureCommandArgs
import de.jxdev.espdmx.utils.CommandDeserializer
import de.jxdev.espdmx.utils.CommandSerializer
import org.junit.Test

import org.junit.Assert.*
import kotlin.reflect.typeOf

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun parseJson() {
        val testJson = """
            {
                "base_command": "CREATE",
                "sub_command": "FIXTURE",
                "args" : {
                    "name": "TestFixture",
                    "channelCount": 4,
                    "channelNames": ["Brightness","R","G","B"],
                    "channelTypes": ["BARE","COLOR_R", "COLOR_G", "COLOR_B"]
                }
            }
        """.trimIndent()

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Command::class.java, CommandDeserializer())
        gsonBuilder.registerTypeAdapter(Command::class.java, CommandSerializer())
        val gson = gsonBuilder.create()

        print(gson.fromJson(testJson, Command::class.java))
    }

    @Test
    fun serializeJson() {
        val command = Command(
            baseCommand = "CREATE",
            subCommand = "FIXTURE",
            args = CreateFixtureCommandArgs(
                channelNames = arrayListOf("test"),
                channelTypes = arrayListOf("BARE"),
                name = "TestFixture",
                channelCount = 1
            )
        )

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Command::class.java, CommandDeserializer())
        gsonBuilder.registerTypeAdapter(Command::class.java, CommandSerializer())
        val gson = gsonBuilder.create()

        print(gson.toJson(command))
    }
}
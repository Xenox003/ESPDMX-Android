package de.jxdev.espdmx

import com.google.gson.GsonBuilder
import de.jxdev.espdmx.model.websocket.Command
import de.jxdev.espdmx.model.websocket.client.CreateFixtureCommandArgs
import de.jxdev.espdmx.model.websocket.client.enums.ChannelType
import de.jxdev.espdmx.utils.serialization.CommandDeserializer
import de.jxdev.espdmx.utils.serialization.CommandSerializer
import org.junit.Test

import org.junit.Assert.*

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
                "sub_command": "SEQUENCE",
                "args" : {
                    "name": "TestSequence",
                    "cues": [
                        {
                            "id": 0,
                            "name": "TestCue0",
                            "type": "CH",
                            "values": [255,0,255],
                            "meta": [1,5,7]
                        },
                        {
                            "id": 1,
                            "name": "TestCue1",
                            "type": "CH",
                            "values": [0,255,0],
                            "meta": [1,5,7]
                        }
                    ]
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
                channelTypes = arrayListOf(ChannelType.BARE),
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
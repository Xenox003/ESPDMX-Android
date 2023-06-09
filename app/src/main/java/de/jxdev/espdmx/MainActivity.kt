package de.jxdev.espdmx

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.rememberNavController
import de.jxdev.espdmx.ui.theme.ESPDMXTheme
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val okHttpClient = OkHttpClient()

        var socketListener: WebSocketListener
        socketListener = WebSocketListener(liveData)

        val websocketURL = "wss://socketsbay.com/wss/v2/1/demo/"
        val webSocket = okHttpClient.newWebSocket(Request.Builder().url(websocketURL).build(),socketListener)
        webSocket.send("test")




        setContent {
            ESPDMXTheme {
                // A surface container using the 'background' color from the them+e

                Navigation()
                //MyScreen(textLive = liveData)


                /*
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Greeting("Android", modifier = Modifier
                            .clickable {
                                Log.d("yeet","lol")
                            })
                    }
                }
                */
            }
        }

        // Prevent the device from entering idle standby \\
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // Fullscreen Lock (Use R API if possible, use FLAG_FULLSCREEN if R API is not available \\
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
            window.insetsController?.hide(WindowInsets.Type.navigationBars())
            window.insetsController?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}

val liveData = MutableLiveData<String>()

@Composable
fun MyScreen (textLive: LiveData<String>) {
    val text: String? by textLive.observeAsState()

    Text (text = "$text")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        fontSize = 30.sp
    )
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ESPDMXTheme {
        Greeting("Android")
    }
}
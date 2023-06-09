package de.jxdev.espdmx.screen

import android.content.Context
import android.net.nsd.NsdManager
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.jxdev.espdmx.utils.ServiceDiscoveryManager


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectionScreen(navController: NavController, context: Context) {
    var text by remember {
        mutableStateOf("")
    }
    val discoveryManager = ServiceDiscoveryManager(context)
    discoveryManager.startDiscovery()


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                value = text,
                singleLine = true,
                onValueChange = {
                    text = it
                },
                textStyle = TextStyle(
                    textAlign = TextAlign.Center
                )
            )
            Row () {
                Button (
                    onClick = {
                        Log.d("TEST","Connecting to $text")
                    }
                ) {
                    Text(text = "Connect")
                }

                Button (
                    onClick = {

                    }
                ) {
                    Text(text = "Discover")
                }
            }
        }
    }
}
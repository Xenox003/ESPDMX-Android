package de.jxdev.espdmx.screen

import android.app.Service
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.jxdev.espdmx.MainActivity
import de.jxdev.espdmx.model.DiscoveredDevice
import de.jxdev.espdmx.utils.ServiceDiscoveryManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectionScreen(navController: NavController, context: Context) {
    val discoveryManagerState = remember { mutableStateOf(ServiceDiscoveryManager(context)) }
    val discoveryManager = discoveryManagerState.value

    val serviceList: List<DiscoveredDevice>? by discoveryManager.liveData.observeAsState()
    val coroutineScope = rememberCoroutineScope()

    var buttonActive by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset((-10).dp, (10).dp),
            onClick = {

                // Stop NSD Discovery --> automatically restarted on onDiscoveryStopped
                discoveryManager.stopDiscovery()

                buttonActive = false

                coroutineScope.launch {
                    delay(1000)
                    buttonActive = true
                }
            },
            enabled = buttonActive
        ) {
            Text(text = "Refresh")
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Device Connection", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Select a Device to connect to:", style = MaterialTheme.typography.bodyMedium)
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                serviceList?.forEach { device ->
                    DevicePanel(context = context, device = device)
                }
                /*
                DevicePanel(context = context, device = DiscoveredDevice(name = "lol", host = null))
                DevicePanel(context = context, device = DiscoveredDevice(name = "lol", host = null))
                DevicePanel(context = context, device = DiscoveredDevice(name = "lol", host = null))
                DevicePanel(context = context, device = DiscoveredDevice(name = "lol", host = null))
                DevicePanel(context = context, device = DiscoveredDevice(name = "lol", host = null))
                DevicePanel(context = context, device = DiscoveredDevice(name = "lol", host = null))
                DevicePanel(context = context, device = DiscoveredDevice(name = "lol", host = null))
                */
            }
        }
    }
}

@Composable
fun DevicePanel(context: Context, device: DiscoveredDevice) {
    Box(modifier = Modifier
        .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(2.dp))
        .fillMaxWidth()
        .clickable {
            Log.d("TEST", "Device ${device.name} clicked!")
        }
    ) {
        Column(modifier =
            Modifier.
                padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 5.dp)
        ) {
            Text(text = device.name.toString(), style = MaterialTheme.typography.labelLarge)
            Text(text = "IP: " + device.host?.hostAddress.toString(), style = MaterialTheme.typography.bodyMedium)
            Text(text = "Port: " + device.port.toString(), style = MaterialTheme.typography.bodyMedium)
        }
    }
    Spacer(modifier = Modifier.padding(5.dp))
}
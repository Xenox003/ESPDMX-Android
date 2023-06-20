package de.jxdev.espdmx.components

import android.content.Context
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.jxdev.espdmx.utils.WebsocketManager
import org.koin.compose.koinInject

@Composable
fun MainTopbar (context : Context, navController: NavController) {
    val socketManager = koinInject<WebsocketManager>()
    val socketIsConnected by socketManager.socketListener.isConnectedLive.observeAsState()
    var programmingMode by remember { mutableStateOf(false) }
    var connectionStatusDialogVisible by remember { mutableStateOf(false) }

    val flashAnimation by rememberInfiniteTransition().animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = 500
                0.0f at 0
                1.0f at 1
                1.0f at 250
                0.0f at 251
                0.0f at 500
            },
            repeatMode = RepeatMode.Restart
        )
    )

    val statusText = if (socketIsConnected == true) "CONNECTED" else "DISCONNECTED"
    val statusColor : Color = if (socketIsConnected == true) Color.Green else Color.Red.copy(flashAnimation)

    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .padding(5.dp)
                .clickable {
                    connectionStatusDialogVisible = !connectionStatusDialogVisible
                },
            text = statusText,
            color = statusColor,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.onSurfaceVariant)
        )
        TopbarButton(
            active = programmingMode,
            onClick = {
                programmingMode = !programmingMode
            },
            text = "Program"
        ) {}

    }

    if (connectionStatusDialogVisible) {
        ConnectionStatusDialog (
            navController = navController,
            setShowDialog = {
                connectionStatusDialogVisible = it
            }
        )
    }
}
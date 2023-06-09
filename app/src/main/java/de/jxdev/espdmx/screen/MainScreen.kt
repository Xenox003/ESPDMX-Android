package de.jxdev.espdmx.screen

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import de.jxdev.espdmx.Screen

@Composable
fun MainScreen (navController: NavController, context : Context) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Connecting to 192.168.178.1...", style = MaterialTheme.typography.headlineMedium)
    }
}
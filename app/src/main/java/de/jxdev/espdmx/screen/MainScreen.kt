package de.jxdev.espdmx.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.jxdev.espdmx.components.DashboardScreenBase
import de.jxdev.espdmx.components.MainTopbar

@Composable
fun MainScreen (navController: NavController, context : Context) {
    DashboardScreenBase(navController = navController, context = context) {
        Text(text = "Main")
    }
}
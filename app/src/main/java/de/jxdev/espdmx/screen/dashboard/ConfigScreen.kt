package de.jxdev.espdmx.screen.dashboard

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import de.jxdev.espdmx.components.DashboardLayout

@Composable
fun ConfigScreen(
    dashboardNavController: NavController,
    context : Context
) {
    Text(text = "Config")
}
package de.jxdev.espdmx.screen

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import de.jxdev.espdmx.components.DashboardScreenBase

@Composable
fun ConfigScreen(context : Context, navController: NavController) {
    DashboardScreenBase(navController = navController, context = context) {
        Text(text = "Config")
    }
}
package de.jxdev.espdmx.screen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.jxdev.espdmx.components.DashboardLayout
import de.jxdev.espdmx.screen.dashboard.ConfigScreen
import de.jxdev.espdmx.screen.dashboard.MainScreen

@Composable
fun DashboardScreen (mainNavController : NavController, context : Context) {
    val dashboardNavController = rememberNavController()

    DashboardLayout(
        mainNavController = mainNavController,
        dashboardNavController = dashboardNavController,
        context = context
    ) {
        NavHost(
            navController = dashboardNavController,
            startDestination = DashboardPage.MainPage.route
        ) {
            composable(route = DashboardPage.MainPage.route) {
                MainScreen(dashboardNavController = dashboardNavController, context = context)
            }
            composable(route = DashboardPage.ConfigPage.route) {
                ConfigScreen(dashboardNavController = dashboardNavController, context = context)
            }
        }
    }
}

sealed class DashboardPage (val route : String) {
    object MainPage : DashboardPage("main_page")
    object ConfigPage : DashboardPage("config_page")
}
package de.jxdev.espdmx

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.jxdev.espdmx.screen.ConnectionScreen
import de.jxdev.espdmx.screen.DashboardScreen

@Composable
fun MainNavigation (context: Context) {
    val mainNavController = rememberNavController()

    NavHost(
        navController = mainNavController,
        startDestination = MainScreen.ConnectionScreen.route
    ) {
        composable(route = MainScreen.ConnectionScreen.route) {
            ConnectionScreen(mainNavController = mainNavController, context = context)
        }
        composable(route = MainScreen.DashboardScreen.route) {
            DashboardScreen(mainNavController = mainNavController, context = context)
        }
    }
}

sealed class MainScreen (val route : String) {
    object ConnectionScreen : MainScreen("connection_screen")
    object DashboardScreen : MainScreen("dashboard_screen")
}
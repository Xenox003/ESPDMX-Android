package de.jxdev.espdmx

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.jxdev.espdmx.screen.ConfigScreen
import de.jxdev.espdmx.screen.ConnectionScreen
import de.jxdev.espdmx.screen.MainScreen

@Composable
fun Navigation (context: Context) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.ConnectionScreen.route
    ) {
        composable(route = Screen.ConnectionScreen.route) {
            ConnectionScreen(navController = navController, context = context)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController, context = context)
        }
        composable(route = Screen.ConfigScreen.route) {
            ConfigScreen(context = context, navController = navController)
        }
    }
}
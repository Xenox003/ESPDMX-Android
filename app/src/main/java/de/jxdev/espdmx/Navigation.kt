package de.jxdev.espdmx

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.jxdev.espdmx.screen.ConnectionScreen
import de.jxdev.espdmx.screen.MainScreen
import de.jxdev.espdmx.utils.WebsocketManager
import org.koin.compose.koinInject

@Composable
fun Navigation (context: Context) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.ConnectionScreen.route) {
        composable(route = Screen.ConnectionScreen.route) {
            ConnectionScreen(navController = navController, context = context)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController, context = context)
        }
        
    }
}
package de.jxdev.espdmx

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.jxdev.espdmx.screen.ConnectionScreen

@Composable
fun Navigation (context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ConnectionScreen.route) {
        composable(route = Screen.ConnectionScreen.route) {
            ConnectionScreen(navController = navController, context = context)
        }
        composable(
            route = Screen.MainScreen.route + "/{name}",
            arguments = listOf(
                navArgument(name = "name") {
                    type = NavType.StringType;
                    defaultValue = "TestUser";
                    nullable = true
                }
            )
        ) {entry ->
            MainScreen(name = entry.arguments?.getString("name"))
        }
        
    }
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectionScreen (navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    )
    {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            navController.navigate(Screen.MainScreen.withArgs(text))
        },
        modifier = Modifier.align(Alignment.End)) {
            Text(text = "To MainScreen")
        }
    }
}
*/

@Composable
fun MainScreen(name: String?) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Hello $name")
    }
}
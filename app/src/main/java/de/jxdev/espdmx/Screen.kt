package de.jxdev.espdmx

sealed class Screen (val route: String) {
    object ConnectionScreen : Screen("connection_screen")
    object MainScreen : Screen("main_screen")

    object ConfigScreen : Screen("config_screen")
}

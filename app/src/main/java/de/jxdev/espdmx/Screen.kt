package de.jxdev.espdmx

sealed class Screen (val route: String) {
    object ConnectionScreen : Screen("connection_screen")
    object MainScreen : Screen("main_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }
}

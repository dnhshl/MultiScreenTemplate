package com.example.multiscreentemplate


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector



/**
 * Contract for information needed on every Rally navigation destination
 */

interface ScreenDestination {
    val icon: ImageVector
    val label: String
    val route: String
}

/**
 * app navigation destinations
 */
object MainScreen : ScreenDestination {
    override val icon = Icons.Filled.Home
    override val label = "Main Screen"
    override val route = "main_screen_route"
}

object SecondScreen : ScreenDestination {
    override val icon = Icons.Filled.Menu
    override val label = "Second Screen"
    override val route = "second_screen_route"
}

object ThirdScreen : ScreenDestination {
    override val icon = Icons.Filled.Settings
    override val label = "Third Screen"
    override val route = "third_screen_route"
}



// Screens to be displayed in the Navigation Bar
val tabScreens = listOf(MainScreen, SecondScreen, ThirdScreen)
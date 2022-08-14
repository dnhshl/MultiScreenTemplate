package com.example.multiscreentemplate

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.multiscreentemplate.ui.MainScreen
import com.example.multiscreentemplate.ui.SecondScreen
import com.example.multiscreentemplate.ui.ThirdScreen
import com.example.multiscreentemplate.ui.util.UiEvent

/**
 * AppNavHost navigates the screens
 */
@Composable
fun AppNavHost(
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    showSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MainScreenDest.route,
        modifier = modifier
    ) {
        composable(route = MainScreenDest.route) {
            MainScreen(
                scaffoldState = scaffoldState,
                onNavigate = navController::navigateSingleTopTo,
                showSnackbar = showSnackbar,
                modifier = modifier
            )
        }
        composable(route = SecondScreenDest.route) {
            SecondScreen(
                onNavigate = navController::navigateSingleTopTo,
                showSnackbar = showSnackbar,
                modifier = modifier
            )
        }
        composable(route = ThirdScreenDest.route) {
            ThirdScreen(
                onNavigate = navController::navigateSingleTopTo,
                showSnackbar = showSnackbar,
                modifier = modifier
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }


fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}

fun NavController.navigateSingleTopTo(event: UiEvent.Navigate) =
    this.navigate(event.route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

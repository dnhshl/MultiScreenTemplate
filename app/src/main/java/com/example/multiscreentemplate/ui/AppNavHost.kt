package com.example.multiscreentemplate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.multiscreentemplate.ui.MainScreen
import com.example.multiscreentemplate.ui.SecondScreen
import com.example.multiscreentemplate.ui.ThirdScreen

/**
 * AppNavHost navigates the screens
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen.route,
        modifier = modifier
    ) {
        composable(route = MainScreen.route) {
            MainScreen(
                modifier = modifier,
                onButtonClick = {
                    navController.navigateSingleTopTo(SecondScreen.route)
                }
            )
        }
        composable(route = SecondScreen.route) {
            SecondScreen(
                modifier = modifier,
                onButtonClickBack = {
                    navController.navigateSingleTopTo(MainScreen.route)
                },
                onButtonClickForward = {
                    navController.navigateSingleTopTo(ThirdScreen.route)
                }
            )
        }
        composable(route = ThirdScreen.route) {
            ThirdScreen(
                modifier = modifier,
                onButtonClick = {
                    navController.navigateSingleTopTo(MainScreen.route)
                }
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




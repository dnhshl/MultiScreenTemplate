package com.example.multiscreentemplate.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Plain class that manages App's UI logic and UI elements' state
class AppState(
    val scaffoldState: ScaffoldState,
    val scope: CoroutineScope,
    val navController: NavHostController

) {

    fun showSnackbar(message: String) {
        scope.launch { scaffoldState.snackbarHostState.showSnackbar(message,"OK")  }

    }
}

@Composable
fun rememberMyAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(
        snackbarHostState = remember { SnackbarHostState() }),
    scope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()

) = remember(scaffoldState, scope, navController) {
    AppState(
        scaffoldState = scaffoldState,
        scope = scope,
        navController = navController
        )
}
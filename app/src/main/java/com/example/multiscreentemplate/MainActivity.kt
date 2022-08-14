package com.example.multiscreentemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.multiscreentemplate.ui.AppState
import com.example.multiscreentemplate.ui.BottomNavigationBar
import com.example.multiscreentemplate.ui.TopBar
import com.example.multiscreentemplate.ui.rememberMyAppState
import com.example.multiscreentemplate.ui.theme.MultiScreenTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {

    MultiScreenTemplateTheme {
        val appState: AppState = rememberMyAppState()
        val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val currentRoute = currentDestination?.route
        val currentScreen = tabScreens.find { it.route == currentRoute } ?: MainScreenDest

        Scaffold(
            scaffoldState = appState.scaffoldState,
            snackbarHost = {
                // reuse default SnackbarHost to have default animation and timing handling
                SnackbarHost(it) { data ->
                    // custom snackbar with the custom colors
                    Snackbar(
                        actionColor = Color.Green,
                        //contentColor = ...,
                        snackbarData = data
                    )
                }
            },
            topBar = { TopBar(currentScreen) },
            bottomBar = { BottomNavigationBar(currentScreen, appState.navController) },
            content = { innerPadding ->
                Box (modifier = Modifier.padding(innerPadding)) {
                    AppNavHost(
                        scaffoldState = appState.scaffoldState,
                        navController = appState.navController,
                        showSnackbar = { message ->
                            appState.showSnackbar(message = message)
                        },
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(color = MaterialTheme.colors.background)
                    )
                }
            }
        )
    }
}
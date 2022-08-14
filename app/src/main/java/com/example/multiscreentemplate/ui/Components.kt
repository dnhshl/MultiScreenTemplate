package com.example.multiscreentemplate.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.multiscreentemplate.ScreenDestination
import com.example.multiscreentemplate.SecondScreenDest
import com.example.multiscreentemplate.navigateSingleTopTo
import com.example.multiscreentemplate.tabScreens
import com.example.multiscreentemplate.ui.util.UiEvent

@Composable
fun TopBar(
    currentScreen: ScreenDestination,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        title = { Text(text = currentScreen.label) },
        navigationIcon = {
            Icon(imageVector = currentScreen.icon,
                contentDescription = null,
                modifier.padding(start=20.dp)
            )},
    )
}


@Composable
fun BottomNavigationBar(
    currentScreen: ScreenDestination,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {

        tabScreens.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.label) },
                label = { Text(text = screen.label) },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.onPrimary.copy(0.8f),
                alwaysShowLabel = true,
                selected = (currentScreen.route == screen.route),
                onClick = {
                    navController.navigateSingleTopTo(screen.route)
                }
            )
        }
    }
}


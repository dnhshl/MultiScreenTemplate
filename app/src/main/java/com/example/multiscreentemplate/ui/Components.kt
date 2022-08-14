package com.example.multiscreentemplate.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


@Composable
fun UnitTextField(
    value: String,
    onValueChange: (String) -> Unit,
    unit: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.primaryVariant,
        fontSize = 70.sp
    ),
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .alignBy(LastBaseline)
        )
        Spacer(modifier = Modifier.width(Spacing.SMALL.dp))
        Text(
            text = unit,
            modifier = Modifier.alignBy(LastBaseline)
        )
    }
}
package com.example.multiscreentemplate.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.multiscreentemplate.*
import com.example.multiscreentemplate.ui.util.UiEvent

@Composable
fun SecondScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    modifier: Modifier = Modifier,
    vm: MainViewModel = hiltViewModel()
) {
    // Auf Events vom ViewModel reagieren
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        vm.uiEvent.collect() { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context = context)
                    )
                }
                else -> Unit
            }
        }
    }


    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(text = "Second Screen")

        OutlinedButton(
            onClick = { onNavigate(UiEvent.Navigate(MainScreenDest.route)) }
        ) { Text("to Main Screen") }

        OutlinedButton(
            onClick = { onNavigate(UiEvent.Navigate(ThirdScreenDest.route)) }
        ) { Text("to Third Screen") }
    }
}

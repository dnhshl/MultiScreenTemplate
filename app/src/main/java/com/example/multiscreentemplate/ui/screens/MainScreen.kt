package com.example.multiscreentemplate.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multiscreentemplate.MainViewModel
import com.example.multiscreentemplate.R
import com.example.multiscreentemplate.SecondScreenDest
import com.example.multiscreentemplate.navigateSingleTopTo
import com.example.multiscreentemplate.ui.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun MainScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    showSnackbar: (String) -> Unit,
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


    if (vm.snackbarMsg.isNotEmpty()) showSnackbar(vm.snackbarMsg)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(text = "First Screen")

        Spacer(modifier = Modifier.height(Spacing.SMALL.dp))

        OutlinedButton(
            onClick = { vm.onSecondScreenButtonClick() }
        ) { Text("to second Screen") }


        OutlinedButton(
            onClick = { vm.onClickCounterButtonClick() }
        ) { Text(stringResource(R.string.click_counter_txt, vm.clickCounter)) }

        OutlinedButton(
            onClick = { vm.informUser(vm.randomString()) }
        ) { Text("show Snackbar") }
    }

}

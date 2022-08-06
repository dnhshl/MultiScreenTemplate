package com.example.multiscreentemplate.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.multiscreentemplate.MainScreenDest
import com.example.multiscreentemplate.MainViewModel
import com.example.multiscreentemplate.navigateSingleTopTo

@Composable
fun ThirdScreen(
    showSnackbar: (String) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    vm: MainViewModel = viewModel()
) {
    if (vm.snackbarMsg.isNotEmpty()) showSnackbar(vm.snackbarMsg)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(text = "Third Screen")

        OutlinedButton(
            onClick = { navController.navigateSingleTopTo(MainScreenDest.route) }
        ) { Text("to Main Screen") }
    }
}

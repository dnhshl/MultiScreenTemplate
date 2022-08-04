package com.example.multiscreentemplate.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multiscreentemplate.MainViewModel

@Composable
fun MainScreen(
    vm: MainViewModel = viewModel(),
    onButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(text = "First Screen")
        OutlinedButton(onClick = onButtonClick) {
            Text("to Second Screen")
        }
    }
}

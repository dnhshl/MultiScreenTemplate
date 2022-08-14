package com.example.multiscreentemplate.ui.util

sealed class UiEvent {
    data class Navigate(val route:String): UiEvent()
    object NavigateUp: UiEvent()
}

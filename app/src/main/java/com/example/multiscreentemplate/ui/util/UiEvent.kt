package com.example.multiscreentemplate.ui.util

sealed class UiEvent {
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackbar(val message: UiText ): UiEvent()
    object NavigateUp: UiEvent()
}

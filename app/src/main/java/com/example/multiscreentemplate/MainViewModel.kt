package com.example.multiscreentemplate



import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multiscreentemplate.preferences.Preferences
import com.example.multiscreentemplate.ui.util.UiEvent
import com.example.multiscreentemplate.ui.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel()
{
    // Channel, um Events vom ViewModel zum UI zu senden
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    var clickCounter by mutableStateOf(0)
        private set


    init {
        Log.i(">>>>>", "loading Preferences")
        val savedPreferences = preferences.loadSavedPreferences()
        clickCounter = savedPreferences.clicks
    }

    // Main Screen Events

    fun onClickCounterButtonClick() {
        clickCounter++
        preferences.saveClicks(clickCounter)
        Log.i(">>>>>", "Saving clickCounter = $clickCounter")
        if (clickCounter % 4 == 0) onFourTimesClicked()
    }

    fun onSecondScreenButtonClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(SecondScreenDest.route))
        }
    }


    fun onFourTimesClicked() {
        viewModelScope.launch {
            _uiEvent.send(
                UiEvent.ShowSnackbar(
                    UiText.StringResource(R.string.four_times_clicked)
                )
            )
            return@launch
        }
    }

    fun onClickShowSnackbar() {
        viewModelScope.launch {
            _uiEvent.send(
                UiEvent.ShowSnackbar(
                    UiText.DynamicString(randomString())
                )
            )
            return@launch
        }
    }

    // Second Screen Events
    // ...

    // helper functions
    fun randomString(length: Int = 5): String {
        val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val randomString = (1..length)
            .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
        return randomString
    }
}
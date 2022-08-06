package com.example.multiscreentemplate



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class MainViewModel: ViewModel()
{
    var snackbarMsg by mutableStateOf("")

    // Hilfsfunktion informUser()
    // setzt snackbarMsg und triggert damit showSnackbar
    fun informUser(msg: String) {
        snackbarMsg = msg
    }



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
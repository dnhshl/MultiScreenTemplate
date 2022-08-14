package com.example.multiscreentemplate.preferences

interface Preferences {

    fun saveClicks(clicks: Int)
    // ...

    fun loadSavedPreferences(): SavedPreferences

    companion object {
        const val KEY_CLICKS = "clicks"
        // ...
    }


}

data class SavedPreferences(
    val clicks: Int,
    // ...
)
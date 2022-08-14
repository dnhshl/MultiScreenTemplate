package com.example.multiscreentemplate.preferences

import android.content.SharedPreferences

class DefaultPreferences(
    private val sharedPreferences: SharedPreferences
): Preferences {
    override fun saveClicks(clicks: Int) {
        sharedPreferences.edit()
            .putInt((Preferences.KEY_CLICKS), clicks)
    }
    // ...

    override fun loadSavedPreferences(): SavedPreferences {
        val clicks = sharedPreferences.getInt(Preferences.KEY_CLICKS, 0)
        return SavedPreferences(
            clicks = clicks
            // ...
        )
    }
}
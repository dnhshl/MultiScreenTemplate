package com.example.multiscreentemplate.preferences

import android.content.SharedPreferences
import android.util.Log

class DefaultPreferences(
    private val sharedPreferences: SharedPreferences
): Preferences {
    override fun saveClicks(clicks: Int) {
        sharedPreferences.edit()
            .putInt((Preferences.KEY_CLICKS), clicks)
            .apply()
    }
    // ...

    override fun loadSavedPreferences(): SavedPreferences {
        val clicks = sharedPreferences.getInt(Preferences.KEY_CLICKS, 3)
        return SavedPreferences(
            clicks = clicks
            // ...
        )
    }
}
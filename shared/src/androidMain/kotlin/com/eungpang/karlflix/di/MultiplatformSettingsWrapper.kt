package com.eungpang.karlflix.di

import android.content.Context
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings

@OptIn(ExperimentalSettingsApi::class)
actual class MultiplatformSettingsWrapper(private val context: Context) {
    companion object {
        private const val preferenceName = "karlflix_pref"
    }
    actual fun createSettings(): ObservableSettings {
        val sharedPreferences =
            context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        return AndroidSettings(delegate = sharedPreferences)
    }
}
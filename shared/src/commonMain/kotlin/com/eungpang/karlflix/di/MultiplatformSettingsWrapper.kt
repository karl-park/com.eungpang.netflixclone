package com.eungpang.karlflix.di

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings

@OptIn(ExperimentalSettingsApi::class)
expect class MultiplatformSettingsWrapper {
    fun createSettings(): ObservableSettings
}
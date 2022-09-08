package com.eungpang.karlflix.di

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import platform.Foundation.NSUserDefaults

@ExperimentalSettingsApi
actual class MultiplatformSettingsWrapper {
    actual fun createSettings(): ObservableSettings {
        val nsUserDefault = NSUserDefaults.standardUserDefaults
        return AppleSettings(delegate = nsUserDefault)
    }
}
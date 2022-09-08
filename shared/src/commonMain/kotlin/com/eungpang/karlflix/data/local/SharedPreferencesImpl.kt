package com.eungpang.karlflix.data.local

import com.eungpang.karlflix.di.MultiplatformSettingsWrapper
import com.eungpang.karlflix.domain.SharedPreferences
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings

@OptIn(ExperimentalSettingsApi::class)
class SharedPreferencesImpl constructor(
    private val platformSettingsWrapper: MultiplatformSettingsWrapper
) : SharedPreferences, Settings by platformSettingsWrapper.createSettings() {

}
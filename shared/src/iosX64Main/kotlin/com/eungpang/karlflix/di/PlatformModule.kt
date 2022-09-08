package com.eungpang.karlflix.di

import com.russhwolf.settings.ExperimentalSettingsApi
import org.koin.core.module.Module
import org.koin.dsl.module

@OptIn(ExperimentalSettingsApi::class)
actual fun platformModule(): Module = module {
    single { MultiplatformSettingsWrapper() }
}

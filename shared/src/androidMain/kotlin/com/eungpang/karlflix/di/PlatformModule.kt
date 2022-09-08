package com.eungpang.karlflix.di

import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { MultiplatformSettingsWrapper(context = get()) }
}
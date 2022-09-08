package com.eungpang.karlflix.di

import com.eungpang.karlflix.data.local.SharedPreferencesImpl
import com.eungpang.karlflix.domain.SharedPreferences
import org.koin.dsl.module

val commonModule = module {
    single<SharedPreferences> {
        SharedPreferencesImpl(
            platformSettingsWrapper = get()
        )
    }
}
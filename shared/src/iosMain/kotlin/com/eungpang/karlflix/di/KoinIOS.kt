package com.eungpang.karlflix.di

import org.koin.core.KoinApplication
import org.koin.dsl.module

fun initKoinIOS(
    doOnStartUp: () -> Unit
): KoinApplication = initKoin(
    module {
        single { doOnStartUp }
    }
)

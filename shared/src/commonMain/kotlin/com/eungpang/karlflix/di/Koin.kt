package com.eungpang.karlflix.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(module: Module): KoinApplication {
    val koinApp = startKoin {
        modules(
            module,
            platformModule(),
            commonModule,
            repositoryModule
        )
    }

    val doOnStartUp = koinApp.koin.get<() -> Unit>()
    doOnStartUp.invoke()

    return koinApp
}

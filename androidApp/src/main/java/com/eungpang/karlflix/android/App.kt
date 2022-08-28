package com.eungpang.karlflix.android

import android.app.Application
import com.eungpang.karlflix.data.MovieRepositoryImpl
import com.eungpang.karlflix.data.remote.KarlflixHttpClient
import com.eungpang.karlflix.domain.repository.MovieRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {
    override fun onCreate() {
        super.onCreate()

         startKoin {
             androidLogger()
             androidContext(this@App)
             modules(appModule)
         }
    }
}

val appModule = module {
    single {
        KarlflixHttpClient(BuildConfig.omdbApiKey)
    }

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }
}

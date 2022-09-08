package com.eungpang.karlflix.di

import com.eungpang.karlflix.data.repository.MovieRepositoryImpl
import com.eungpang.karlflix.data.repository.PlanRepositoryImpl
import com.eungpang.karlflix.data.repository.UserRepositoryImpl
import com.eungpang.karlflix.domain.repository.MovieRepository
import com.eungpang.karlflix.domain.repository.PlanRepository
import com.eungpang.karlflix.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }

    single<PlanRepository> {
        PlanRepositoryImpl()
    }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}

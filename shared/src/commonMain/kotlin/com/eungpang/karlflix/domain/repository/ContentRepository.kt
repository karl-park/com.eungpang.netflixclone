package com.eungpang.karlflix.domain.repository

interface ContentRepository<T> {
    suspend fun retrieveContent(): Result<T>
}

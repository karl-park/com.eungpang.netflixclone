package com.eungpang.karlflix.domain.repository

import com.eungpang.karlflix.domain.model.Movie

interface MovieRepository {
    suspend fun searchMovie(title: String, page: Int = 1, type: String = "") : Result<List<Movie>>
}
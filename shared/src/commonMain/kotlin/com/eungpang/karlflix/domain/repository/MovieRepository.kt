package com.eungpang.karlflix.domain.repository

import com.eungpang.karlflix.domain.model.Movie
import com.eungpang.karlflix.domain.model.SectionType

interface MovieRepository {
    suspend fun searchMovie(title: String, page: Int = 1, type: String = "") : Result<List<Movie>>

    suspend fun getCuratedMovies(sectionType: SectionType) : Result<List<Movie>>

}
package com.eungpang.karlflix.data.repository

import com.eungpang.karlflix.data.remote.KarlflixHttpClient
import com.eungpang.karlflix.domain.model.Movie
import com.eungpang.karlflix.domain.model.SectionType
import com.eungpang.karlflix.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val httpClient: KarlflixHttpClient,
): MovieRepository {
    override suspend fun searchMovie(title: String, page: Int, type: String) : Result<List<Movie>> {
        return httpClient.searchMovie(title, page, type)
    }

    override suspend fun getCuratedMovies(sectionType: SectionType): Result<List<Movie>> {
        val title = listOf("home", "love", "baby", "summer").random()
        val page = (0..100).random()
        val type = ""
        return httpClient.searchMovie(title, page, type)
    }


}

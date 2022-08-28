package com.eungpang.karlflix.data

import com.eungpang.karlflix.data.remote.KarlflixHttpClient
import com.eungpang.karlflix.domain.model.Movie
import com.eungpang.karlflix.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val httpClient: KarlflixHttpClient,
): MovieRepository {
    override suspend fun searchMovie(title: String, page: Int, type: String) : Result<List<Movie>> {
        return httpClient.searchMovie(title, page, type)
    }
}

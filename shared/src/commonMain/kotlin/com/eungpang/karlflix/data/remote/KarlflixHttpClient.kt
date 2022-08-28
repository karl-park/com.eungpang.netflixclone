package com.eungpang.karlflix.data.remote

import com.eungpang.karlflix.data.remote.MovieResponse.Companion.toDomain
import com.eungpang.karlflix.domain.model.Movie
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class KarlflixHttpClient(
//    private val movieService: MovieService,
    private val apiKey: String,
) {
    companion object {
        private const val BASE_URL = "http://www.omdbapi.com/?"
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    private val movieService: MovieService = object : MovieService {
        @Throws(Exception::class)
        override suspend fun searchMovie(title: String, page: Int, type: String): MovieSearchResponse {
            val url = StringBuilder().apply {
                append(BASE_URL)
                append("apikey=$apiKey")
                append("&s=$title")
                if (page in 1..100) append("&page=$page")
                if (listOf("movie", "series", "episode").contains(type)) append("&type=$type")
            }.toString()
            return httpClient.get(url).body()
        }

    }

    suspend fun searchMovie(title: String, page: Int = 1, type: String = "") : Result<List<Movie>> {
        val movieSearchResponse = movieService.searchMovie(title, page, type)
        if (movieSearchResponse.isSuccess.lowercase() != "true") return Result.failure(RuntimeException("API Failure"))

        return Result.success(movieSearchResponse.data.map {
            it.toDomain()
        })
    }
}

interface MovieService {
    @Throws(Exception::class)
    suspend fun searchMovie(
        title: String,
        page: Int = 1,
        type: String = "", // movie|series|episode
    ): MovieSearchResponse
}


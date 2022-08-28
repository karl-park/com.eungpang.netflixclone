package com.eungpang.karlflix.data.remote

import com.eungpang.karlflix.domain.model.DomainMapper
import com.eungpang.karlflix.domain.model.Movie
import com.eungpang.karlflix.domain.model.Type
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MovieSearchResponse(
    @SerialName("Search") val data: List<MovieResponse>,
    @SerialName("totalResults") val totalCount: String, // Int
    @SerialName("Response") val isSuccess: String // True/false
)

@kotlinx.serialization.Serializable
data class MovieResponse(
    @SerialName("Title") val title: String,
    @SerialName("Year") val year: String,
    @SerialName("imdbID") val imdbId: String,
    @SerialName("Type") val type: String,
    @SerialName("Poster") val posterUrl: String
) {
    companion object : DomainMapper<MovieResponse, Movie> {
        override fun MovieResponse.toDomain(): Movie = Movie(
            title = title,
            year = year,
            imdbId = imdbId,
            type = Type.fromValue(type),
            posterUrl = posterUrl
        )
    }
}
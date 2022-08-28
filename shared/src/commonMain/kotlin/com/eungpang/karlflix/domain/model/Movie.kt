package com.eungpang.karlflix.domain.model

//{
//    "Title": "Daddy's Home",
//    "Year": "2015",
//    "imdbID": "tt1528854",
//    "Type": "movie",
//    "Poster": "https://m.media-amazon.com/images/M/MV5BMTQ0OTE1MTk4N15BMl5BanBnXkFtZTgwMDM5OTk5NjE@._V1_SX300.jpg"
//}

data class Movie(
    val title: String,
    val year: String,
    val imdbId: String,
    val type: Type,
    val posterUrl: String
)

enum class Type(val text: String) {
    Movie("movie"),
    Series("series"),
    Episode("episode")
}
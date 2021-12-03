package com.example.movies.data

import android.view.animation.Animation
import com.squareup.moshi.Json

data class Response(

    @Json(name = "page")
    val page: Int? = null,

    @Json(name = "total_pages")
    val totalPages: Int? = null,

    @Json(name = "results")
    val results: List<ResultsItem?>? = null,

    @Json(name = "total_results")
    val totalResults: Int? = null
)

data class ResultsItem(

    @Json(name = "overview")
    val overview: String? = null,

    @Json(name = "original_language")
    val originalLanguage: String? = null,

    @Json(name = "original_title")
    val originalTitle: String? = null,

    @Json(name = "video")
    val video: Boolean? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "genre_ids")
    val genreIds: List<Int?>? = null,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "release_date")
    val releaseDate: String? = null,

    @Json(name = "popularity")
    val popularity: Double? = null,

    @Json(name = "vote_average")
    val voteAverage: Double? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "adult")
    val adult: Boolean? = null,

    @Json(name = "vote_count")
    val voteCount: Int? = null
)

//data class Genre(val genreName: String, val id: Int)
//
//val allGenre: List<Genre> =
//	listOf(
//		Genre("Action", 28),
//		Genre("Adventure", 12),
//		Genre("Animation", 16),
//		Genre("Comedy", 35),
//		Genre("Crime", 80),
//		Genre("Documentary", 99),
//		Genre("Drama", 18),
//		Genre("Family", 10751),
//		Genre("Fantasy", 14),
//		Genre("History", 36),
//		Genre("Horror", 27),
//		Genre("Music", 10402),
//		Genre("Mystery", 9648),
//		Genre("Romance", 10749),
//		Genre("Science Fiction ", 878),
//		Genre("TV Movie ", 10770),
//		Genre("Thriller", 53),
//		Genre("War", 10752),
//		Genre("Western", 37)
//	)


//val allGenre : Map<Int, String> =
//	mapOf(
//		(28 to "Action"),
//		(12 to "Adventure"),
//		(16 to "Animation"),
//		(35 to "Comedy"),
//		(80 to "Crime"),
//		(99 to "Documentary"),
//		(18 to "Drama"),
//		(10751 to "Family"),
//		(14 to "Fantasy"),
//		(36 to "History"),
//		(27 to "Horror"),
//		(10402 to "Music"),
//		(9648 to "Mystery"),
//		(10749 to "Romance"),
//		(878 to "Science Fiction "),
//		(10770 to "TV Movie "),
//		(53 to "Thriller"),
//		(10752 to "War"),
//		(37 to "Western")
//	)



















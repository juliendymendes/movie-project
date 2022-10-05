package com.me.movieproject.model

import com.squareup.moshi.Json

data class Movie (
    val id: Int,
    val overview: String,
    val popularity: Double,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "release_date") val releaseDate: String,
    val title: String,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "backdrop_path") val backdropPath: String
    )
package com.me.movieproject.model

data class MoviesResponse (
    val page: Int,
    val results: List<Movie>
)
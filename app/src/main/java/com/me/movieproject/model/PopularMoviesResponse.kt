package com.me.movieproject.model

data class PopularMoviesResponse (
    val page: Int,
    val results: List<Movie>
)
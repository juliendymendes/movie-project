package com.me.movieproject.listeners

import com.me.movieproject.model.Movie

class MovieListener(val clickListener: (movie:Movie) -> Unit) {

    fun onClick(movie: Movie){
        clickListener(movie)
    }
}
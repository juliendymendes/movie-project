package com.me.movieproject.listeners

import com.me.movieproject.model.Genre

class GenreListener(val clickListener: (g: Genre) -> Unit){
    fun onClick(genre: Genre) {
        clickListener(genre)
    }
}
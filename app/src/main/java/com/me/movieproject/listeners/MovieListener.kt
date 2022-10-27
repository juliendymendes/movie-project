package com.me.movieproject.listeners

import androidx.navigation.fragment.NavHostFragment
import com.me.movieproject.generated.callback.OnClickListener
import com.me.movieproject.model.Movie
import com.me.movieproject.viewmodel.MovieViewModel

class MovieListener(val clickListener: (movie:Movie) -> Unit) {

    fun onClick(movie: Movie){
        clickListener(movie)
    }
}
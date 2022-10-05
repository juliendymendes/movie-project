package com.me.movieproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.movieproject.model.Movie
import com.me.movieproject.repositories.MovieRepository
import com.me.movieproject.repositories.Result
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {
    private val movieRepository = MovieRepository()

    private var _popularMovies = MutableLiveData<List<Movie>?>()
    val popularMovies : LiveData<List<Movie>?> = _popularMovies

    private var _status = MutableLiveData<String?>()
    val status : LiveData<String?> = _status

    init {
        getPopularMoviesList()
    }

    private fun getPopularMoviesList(){
        viewModelScope.launch {
            when(val result = movieRepository.loadPopularMovies()){
                is Result.Success<List<Movie>> -> _popularMovies.value = result.data

                is Result.Error -> _status.value = result.exception.message
            }
        }
    }
}
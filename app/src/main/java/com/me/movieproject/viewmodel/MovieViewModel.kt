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

    private var _moviesByGenres = MutableLiveData<List<Movie>?>()
    val moviesByGenres : LiveData<List<Movie>?> = _moviesByGenres

    private var _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie : LiveData<Movie> = _selectedMovie

    private var _movie = MutableLiveData<Movie?>()
    val movie: LiveData<Movie?> = _movie

    private var _status = MutableLiveData<String?>()
    val status : LiveData<String?> = _status

    init {
        getPopularMoviesList()
    }

    fun getPopularMoviesList(){
        viewModelScope.launch {
            when(val result = movieRepository.loadPopularMovies()){
                is Result.Success<List<Movie>> -> _popularMovies.value = result.data

                is Result.Error -> _status.value = result.exception.message
            }
        }

    }

    fun getMovieByGenresList(genres_ids: List<Int>){
        viewModelScope.launch {
            when(val result = movieRepository.loadMoviesByGenres(genres_ids)){
                is Result.Success<List<Movie>> -> _moviesByGenres.value = result.data

                is Result.Error -> _status.value = result.exception.message
            }
        }
    }

    fun getMovieDetails(movie: Movie){

        viewModelScope.launch {
            when(val result = movieRepository.loadMovieById(movie.id)){
                is Result.Success<Movie> -> {
                    _movie.value = result.data
                    println(result.data)
                }

                is Result.Error -> _status.value = result.exception.message
            }
        }
    }

    fun setSelectedMovie(movie: Movie){
        _selectedMovie.value = movie
    }
}
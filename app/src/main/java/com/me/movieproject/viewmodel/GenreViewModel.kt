package com.me.movieproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.movieproject.api.Api
import com.me.movieproject.model.Genre
import com.me.movieproject.model.GenresResponse
import com.me.movieproject.model.Movie
import com.me.movieproject.repositories.MovieRepository
import com.me.movieproject.repositories.Result
import kotlinx.coroutines.launch

class GenreViewModel(
): ViewModel() {
    private val movieRepository = MovieRepository()

    private var _genres = MutableLiveData<List<Genre>?>()
    val genres : LiveData<List<Genre>?> = _genres

    private var _status = MutableLiveData<String?>()
    val status : LiveData<String?> = _status

    init {
        getGenreList()
    }

    private fun getGenreList(){
        viewModelScope.launch {
            when(val result = movieRepository.loadGenres()){
                is Result.Success<List<Genre>> -> _genres.value = result.data

                is Result.Error -> _status.value = result.exception.message
            }
        }
    }

}
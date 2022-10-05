package com.me.movieproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.movieproject.model.Genre
import com.me.movieproject.repositories.MovieRepository
import com.me.movieproject.repositories.Result
import kotlinx.coroutines.launch


class GenreViewModel(
): ViewModel() {

    private var _genres = MutableLiveData<List<Genre>?>()
    val genres : LiveData<List<Genre>?> = _genres

    init {
        getGenreList()
    }

    private fun getGenreList(){
        val movieRepository = MovieRepository()
        viewModelScope.launch {
            when(val result = movieRepository.loadGenres()){
                is Result.Success<List<Genre>> -> {
                    _genres.value = result.data
                    println("YAY")
                }
                else -> println("OH NO")
            }


        }
    }

}
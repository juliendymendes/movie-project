package com.me.movieproject.repositories

import com.me.movieproject.api.Api
import com.me.movieproject.model.Genre
import com.me.movieproject.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

class MovieRepository {

    suspend fun loadGenres(): Result<List<Genre>> {
        return withContext(Dispatchers.IO){
            try {
                return@withContext Result.Success(Api.api.getGenres().genres)

            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }

    suspend fun loadPopularMovies(): Result<List<Movie>>{
        return withContext(Dispatchers.IO){
            try{
                return@withContext Result.Success(Api.api.getPopularMovies().results)
            }catch (e: Exception){
                return@withContext Result.Error(e)
            }
        }
    }

    suspend fun loadMoviesByGenres(genres_ids : List<Int>): Result<List<Movie>> {
        return withContext(Dispatchers.IO){
            try {
                return@withContext Result.Success(Api.api.getMoviesByGenres(genres_ids = genres_ids).results)
            }catch (e: Exception){
                return@withContext Result.Error(e)
            }
        }
    }

    suspend fun loadMovieById(id: Int): Result<Movie>{
        return withContext(Dispatchers.IO){
            try{
                return@withContext Result.Success(Api.api.getMovieByID(id))
            }catch (e: Exception){
                return@withContext Result.Error(e)
            }
        }
    }

}
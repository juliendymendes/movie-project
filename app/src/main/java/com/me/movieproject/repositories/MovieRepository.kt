package com.me.movieproject.repositories

import com.me.movieproject.api.Api
import com.me.movieproject.model.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
private const val API_KEY = "9d24789efabca4c463fa0234a6ca34ff"


class MovieRepository {

    suspend fun loadGenres(): Result<List<Genre>> {

        return withContext(Dispatchers.IO){
            try {
                Result.Success(Api.api.getGenres(API_KEY).genres)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

}
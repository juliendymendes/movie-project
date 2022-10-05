package com.me.movieproject.api

import com.me.movieproject.model.Genre
import com.me.movieproject.model.GenresResponse
import com.me.movieproject.model.Movie
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "9d24789efabca4c463fa0234a6ca34ff"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService{

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") api_key: String = API_KEY): GenresResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key: String = API_KEY): List<Movie>
}

object Api{
    val api: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}

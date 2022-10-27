package com.me.movieproject.api

import com.me.movieproject.model.GenresResponse
import com.me.movieproject.model.Movie
import com.me.movieproject.model.MovieResponse
import com.me.movieproject.model.MoviesResponse
import com.me.movieproject.utils.API_KEY
import com.me.movieproject.utils.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


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
    suspend fun getPopularMovies(@Query("api_key") api_key: String = API_KEY): MoviesResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenres(@Query("api_key") api_key: String = API_KEY, @Query("with_genres") genres_ids: List<Int>): MoviesResponse

    @GET("movie/{id}")
    suspend fun getMovieByID(@Path("id") id: Int, @Query("api_key") api_key: String = API_KEY): Movie
}

object Api{
    val api: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}

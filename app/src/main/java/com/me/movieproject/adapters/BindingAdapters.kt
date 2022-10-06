package com.me.movieproject.adapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.me.movieproject.model.Genre
import com.me.movieproject.model.Movie
import com.me.movieproject.utils.IMAGE_BASE_URL

@BindingAdapter("genresList")
fun bindGenresList(recyclerView: RecyclerView,
                     data: List<Genre>?) {
    val adapter = recyclerView.adapter as GenresListAdapter
    adapter.submitList(data)
}

@BindingAdapter("popularMoviesList")
fun bindPopularMoviesList(recyclerView: RecyclerView,
                     data: List<Movie>?) {
    val adapter = recyclerView.adapter as PopularMoviesListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgPath: String?){

    imgPath?.let {
        val imgUrl = IMAGE_BASE_URL + "w200" + it
        val imgUri = imgUrl.toUri().buildUpon().build()
        imageView.load(imgUri)
    }



}

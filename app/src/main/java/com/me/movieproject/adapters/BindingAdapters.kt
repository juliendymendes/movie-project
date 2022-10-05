package com.me.movieproject.adapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.me.movieproject.R
import com.me.movieproject.api.BASE_URL
import com.me.movieproject.model.Genre

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Genre>?) {
    val adapter = recyclerView.adapter as GenresListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgPath: String){
    val imgUrl = BASE_URL
    imgUrl.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imgUri)
    }
}

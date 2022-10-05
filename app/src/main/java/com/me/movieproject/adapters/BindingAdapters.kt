package com.me.movieproject.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.me.movieproject.model.Genre

@BindingAdapter("listData")
fun bindGenresList(recyclerView: RecyclerView,
                     data: List<Genre>?) {
    val adapter = recyclerView.adapter as GenresListAdapter
    adapter.submitList(data)
}

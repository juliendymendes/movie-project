package com.me.movieproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.me.movieproject.databinding.PopularMoviesListItemBinding
import com.me.movieproject.model.Movie

class PopularMoviesListAdapter
    : ListAdapter<Movie, PopularMoviesListAdapter.PopularMoviesViewHolder>(DiffCallback) {

    class PopularMoviesViewHolder(private var binding: PopularMoviesListItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(Movie: Movie){
            binding.movie = Movie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(PopularMoviesListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

    }
}
package com.me.movieproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.me.movieproject.databinding.MoviesByGenresListItemBinding
import com.me.movieproject.listeners.MovieListener
import com.me.movieproject.model.Movie

class MoviesByGenresListAdapter(val clickListener: MovieListener)
    : ListAdapter<Movie, MoviesByGenresListAdapter.MoviesByGenresViewHolder>(DiffCallback) {

        class MoviesByGenresViewHolder(private var binding: MoviesByGenresListItemBinding)
            : RecyclerView.ViewHolder(binding.root){
            fun bind(movie: Movie, clickListener: MovieListener){
                binding.movie = movie
                binding.clickListener = clickListener
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : MoviesByGenresViewHolder {
            return MoviesByGenresViewHolder(
                MoviesByGenresListItemBinding.inflate(
                    LayoutInflater.from(parent.context)
                )
            )
        }

        override fun onBindViewHolder(holder: MoviesByGenresViewHolder, position: Int) {
           holder.bind(getItem(position), clickListener)
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
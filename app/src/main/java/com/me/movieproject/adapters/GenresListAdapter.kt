package com.me.movieproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.me.movieproject.databinding.GenresListItemBinding
import com.me.movieproject.model.Genre

class GenresListAdapter: ListAdapter<Genre, GenresListAdapter.GenreViewHolder>(DiffCallback) {
    class GenreViewHolder(private var binding: GenresListItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(genre: Genre){
                binding.genre = genre
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(GenresListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Genre>(){
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.name == newItem.name
        }

    }
}
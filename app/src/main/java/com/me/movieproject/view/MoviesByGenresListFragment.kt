package com.me.movieproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.me.movieproject.R
import com.me.movieproject.adapters.MoviesByGenresListAdapter
import com.me.movieproject.databinding.FragmentMoviesByGenresListBinding
import com.me.movieproject.listeners.MovieListener
import com.me.movieproject.model.Movie
import com.me.movieproject.viewmodel.MovieViewModel


class MoviesByGenresListFragment : Fragment() {

    private lateinit var binding: FragmentMoviesByGenresListBinding
    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesByGenresListBinding.inflate(layoutInflater)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            moviesByGenresList.adapter = MoviesByGenresListAdapter(MovieListener { onMovieClick(it) })
            movieViewModel = this@MoviesByGenresListFragment.movieViewModel
        }

        return binding.root
    }

    private fun onMovieClick(movie: Movie){
        movieViewModel.setSelectedMovie(movie)
        movieViewModel.getMovieDetails(movie)
        val bundle = Bundle()
        bundle.putString("movie_title",  movie.title)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_moviesByGenresListFragment_to_detailsFragment, bundle)
    }
}
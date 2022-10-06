package com.me.movieproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.me.movieproject.R
import com.me.movieproject.adapters.GenresListAdapter
import com.me.movieproject.adapters.PopularMoviesListAdapter
import com.me.movieproject.databinding.FragmentListBinding
import com.me.movieproject.listeners.GenreListener
import com.me.movieproject.model.Genre
import com.me.movieproject.viewmodel.GenreViewModel
import com.me.movieproject.viewmodel.MovieViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val genreViewModel: GenreViewModel by activityViewModels()
    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.genresList.adapter = GenresListAdapter(GenreListener { onGenreClick(it) })
        binding.genreViewModel = genreViewModel
        binding.popularMoviesList.adapter = PopularMoviesListAdapter()
        binding.movieViewModel = movieViewModel

        return binding.root
    }

    private fun onGenreClick(genre: Genre){
        genreViewModel.setSelectedGenre(genre)
        movieViewModel.getMovieByGenresList(listOf(genre.id))
        findNavController().navigate(R.id.action_listFragment_to_moviesByGenresListFragment)
    }

}
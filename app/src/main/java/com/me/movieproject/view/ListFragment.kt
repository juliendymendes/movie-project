package com.me.movieproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.utils.widget.MockView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.me.movieproject.R
import com.me.movieproject.adapters.GenresListAdapter
import com.me.movieproject.adapters.PopularMoviesListAdapter
import com.me.movieproject.databinding.FragmentListBinding
import com.me.movieproject.listeners.GenreListener
import com.me.movieproject.listeners.MovieListener
import com.me.movieproject.model.Genre
import com.me.movieproject.model.Movie
import com.me.movieproject.viewmodel.GenreViewModel
import com.me.movieproject.viewmodel.MovieViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val genreViewModel: GenreViewModel by activityViewModels()
    private val movieViewModel: MovieViewModel by activityViewModels()

    private var filteredMovies: List<Movie>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.genresList.adapter = GenresListAdapter(GenreListener { onGenreClick(it) })
        binding.genreViewModel = genreViewModel
        binding.popularMoviesList.adapter = PopularMoviesListAdapter(MovieListener { onMovieClick(it) })
        binding.movieViewModel = movieViewModel
        binding.swipeLayout.setOnRefreshListener {
            movieViewModel.getPopularMoviesList()
            binding.swipeLayout.isRefreshing = false
        }

        binding.btnSearch.setOnClickListener{
            filterMovies(binding.etSearch.text.toString())
        }

        return binding.root
    }

    private fun onGenreClick(genre: Genre){
        genreViewModel.setSelectedGenre(genre)
        movieViewModel.getMovieByGenresList(listOf(genre.id))
        val bundle = Bundle()
        bundle.putString("genre_name",  genre.name)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_listFragment_to_moviesByGenresListFragment, bundle)
    }

    private fun onMovieClick(movie: Movie){
        movieViewModel.setSelectedMovie(movie)
        movieViewModel.getMovieDetails(movie)
        val bundle = Bundle()
        bundle.putString("movie_title",  movie.title)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_listFragment_to_detailsFragment, bundle)
    }

    private fun filterMovies(searchText: String){
        val searchTextLowercase = searchText.lowercase()

        filteredMovies = movieViewModel.popularMovies.value?.filter {
                movie -> movie.title.lowercase().contains(searchTextLowercase)
        }
        filteredMovies?.forEach{
            println(it.title)
        }
    }

}
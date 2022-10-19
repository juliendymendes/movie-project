package com.me.movieproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.me.movieproject.R
import com.me.movieproject.databinding.FragmentDetailsBinding
import com.me.movieproject.viewmodel.MovieViewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.movieViewModel = this.movieViewModel
        movieViewModel.movie.observe(viewLifecycleOwner){
            println(it?.title)
        }

        movieViewModel.status.observe(viewLifecycleOwner){
            println(it)
        }
        return binding.root
    }
}
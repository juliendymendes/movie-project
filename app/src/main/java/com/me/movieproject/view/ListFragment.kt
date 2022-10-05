package com.me.movieproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.me.movieproject.R
import com.me.movieproject.adapters.GenresListAdapter
import com.me.movieproject.databinding.FragmentListBinding
import com.me.movieproject.model.Genre
import com.me.movieproject.viewmodel.GenreViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val genreViewModel: GenreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.genresList.adapter = GenresListAdapter()
        binding.genreViewModel = genreViewModel

        return binding.root
    }

}
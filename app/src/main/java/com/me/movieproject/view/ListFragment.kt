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
import com.me.movieproject.viewmodel.GenreViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val genreViewModel: GenreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            genresList.adapter = GenresListAdapter()
        }

        return binding.root
    }

}
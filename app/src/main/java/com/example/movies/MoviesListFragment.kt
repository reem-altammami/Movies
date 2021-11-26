package com.example.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movies.adapter.MoviesAdapter
import com.example.movies.databinding.FragmentMoviesListBinding


class MoviesListFragment : Fragment() {

    private val sharedViewModel : MoviesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMoviesListBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = sharedViewModel
        binding.gridRecyclerView.adapter = MoviesAdapter()
        return binding.root


    }
    fun goDetelis(){

    }

}
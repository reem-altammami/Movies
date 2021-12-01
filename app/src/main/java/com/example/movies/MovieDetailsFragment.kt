package com.example.movies

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.example.movies.databinding.FragmentMovieDetailsBinding


class MovieDetailsFragment : Fragment() {

    private val sharedViewModel : MoviesViewModel by activityViewModels()
    var movieIndex: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            movieIndex = it!!.getInt("index")

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedViewModel.showDetails(movieIndex)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = sharedViewModel
        // Inflate the layout for this fragment
        return binding.root    }


}
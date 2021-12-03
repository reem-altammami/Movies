package com.example.movies

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.movies.databinding.FragmentMovieDetailsBinding
import com.example.movies.details.DetailsViewModel
import android.R
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController


class MovieDetailsFragment : Fragment() {

    private val sharedViewModel: DetailsViewModel by activityViewModels()
    var movieId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            movieId = it!!.getInt("movieId")

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMovieDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = sharedViewModel

        Log.e("TAG", "Reem 110 :${movieId}")

        sharedViewModel.getMovieDetails(movieId)
        sharedViewModel.movieDetails?.observe(this.viewLifecycleOwner, {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = it?.title

        })
// share content
        binding.shear.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
                .putExtra(
                    Intent.EXTRA_TEXT,
                    " ${sharedViewModel.movieDetails.value?.title} link : https://www.themoviedb.org/movie/${sharedViewModel.movieDetails.value?.id}"
                )
                .setType("text/plain")
            if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
                startActivity(intent)
            }
        }

        // Add navigation to toolbar
        val toolbar: Toolbar = binding.toolBar
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)!!
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener() {
            findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentToMoviesListFragment2())
        }


        return binding.root
    }
//  Reset details page
    override fun onStop() {
        super.onStop()
        sharedViewModel.resetMovie()

    }


}
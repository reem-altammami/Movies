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


class MovieDetailsFragment : Fragment() {

    private val sharedViewModel : DetailsViewModel by activityViewModels()
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

        Log.e("TAG","Reem 110 :${movieId}")

        sharedViewModel.getMovieDetails(movieId)

        sharedViewModel.movieDetails?.observe(this.viewLifecycleOwner,{
            (requireActivity() as AppCompatActivity).supportActionBar?.title=it?.title

        })
        binding.shareimagebutton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_TEXT," ${sharedViewModel.movieDetails.value?.title} link : https://www.themoviedb.org/movie/${sharedViewModel.movieDetails.value?.id}")
                .setType("text/plain")
                if (activity?.packageManager?.resolveActivity(intent , 0) !=null){
                    startActivity(intent)
                }
        }



        return binding.root    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


}
package com.example.movies.details

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.MoviesApiStatus
import com.example.movies.data.Movie
import com.example.movies.network.MoviesApi
import kotlinx.coroutines.launch
import java.time.LocalDate

class DetailsViewModel : ViewModel() {

    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    private val _movieDetails = MutableLiveData<Movie?>()
    var movieDetails: MutableLiveData<Movie?> = _movieDetails

// get more details from server by movie id
    fun getMovieDetails(id: Int) {
        Log.e("TAG", "getMovieDetails out  lan out try:${id}")
        viewModelScope.launch {
            Log.e("TAG", "getMovieDetails in lan out try:${id}")
            try {
                Log.e("TAG", "getMovieDetails in lan in try 1:${id}")
                val movieDetails = MoviesApi.retrofitService.getMovieDetails(id)
                _movieDetails.value = movieDetails

            } catch (e: Exception) {
                Log.e("TAG", "Reem:${e}")
            }
        }
    }
//    fun showDetails() {
//        Log.e("TAG", "showDetails: ${ movieDetails.value?.title}", )
//        title.value = movieDetails.value?.title
//        overview.value = movieDetails.value?.overview
//
//        poster.value = movieDetails.value?.posterPath
//
//        releaseDate.value = movieDetails.value?.releaseDate
//        rate.value = movieDetails.value?.voteAverage
//        // getYear(releaseDate.value)
////        setGenre(position)
//    }

    fun resetMovie() {
        _movieDetails.value = Movie()
    }
}
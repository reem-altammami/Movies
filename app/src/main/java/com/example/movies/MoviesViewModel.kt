package com.example.movies

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.Movie
import com.example.movies.data.ResultsItem
import com.example.movies.network.MovieApiFilter
import com.example.movies.network.MoviesApi
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate


enum class MoviesApiStatus{
    LOADING,ERROR,DONE
}
class MoviesViewModel : ViewModel() {
    private val _movies = MutableLiveData<List<ResultsItem?>>()

    // The external immutable LiveData for the request status
    var movies: MutableLiveData<List<ResultsItem?>> = _movies

    private val _status = MutableLiveData<MoviesApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<MoviesApiStatus> = _status



    init {
        getMoviesList()
    }

     fun getMoviesList() {
        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING

            try {
                val listResult = MoviesApi.retrofitService.getMovies(4)
                _status.value = MoviesApiStatus.DONE
                _movies.value = listResult.results
            } catch (e: Exception) {
                _status.value = MoviesApiStatus.ERROR
                _movies.value = listOf()

            }
        }
    }
    fun getMovieGenre(filter: MovieApiFilter){

        viewModelScope.launch {
            try{
                val listMovieGenre = MoviesApi.retrofitService.getMoviesByGenre(filter.genre)
                _movies.value = listMovieGenre.results
            } catch (e: java.lang.Exception){

            }
        }
    }


    fun showList() {
        getMoviesList()
    }



    fun search(movieName: String) {
        val nameMovie = movies.value?.find { it?.title?.toLowerCase() == movieName.toLowerCase() }
        val searchList = mutableListOf<ResultsItem?>()
        searchList.add(nameMovie)
        _movies.value = searchList

    }

    fun sortListAlpha() {
        val sortedList = movies.value?.sortedBy { it?.title?.toLowerCase() }
        _movies.value = sortedList
    }

    fun sortListRate() {
        val sortedList = movies.value?.sortedByDescending { it?.voteAverage!! }
        _movies.value = sortedList
    }

    fun sortListReleaseDate() {
        val sortedList =
            movies.value?.sortedBy { SimpleDateFormat("yyyy-MM-dd").parse(it?.releaseDate!!) }
        _movies.value = sortedList
    }


    fun updateFilter(filter:MovieApiFilter){
        getMovieGenre(filter)
    }
}
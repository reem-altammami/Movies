package com.example.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.Response
import com.example.movies.data.ResultsItem
import com.example.movies.network.MoviesApi
import kotlinx.coroutines.launch



enum class MoviesApiStatus{
    LOADING,ERROR,DONE
}
class MoviesViewModel : ViewModel() {
    private val _movies = MutableLiveData<List<ResultsItem?>>()

    // The external immutable LiveData for the request status
    val movies: LiveData<List<ResultsItem?>> = _movies

    private val _status = MutableLiveData<MoviesApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<MoviesApiStatus> = _status

    init {
        getMoviesList()
    }

    private fun getMoviesList() {
        viewModelScope.launch {
            try {
                val listResult = MoviesApi.retrofitService.getMovies()
                _status.value = MoviesApiStatus.DONE
                _movies.value = listResult.results
            } catch (e: Exception) {
                _status.value = MoviesApiStatus.ERROR
                _movies.value= listOf()

            }
    }
}
}
package com.example.movies

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.ResultsItem
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
    val title = MutableLiveData<String>()
    val overview = MutableLiveData<String>()
    val poster = MutableLiveData<String>()
    val releaseDate = MutableLiveData<String>()
    val releaseYear = MutableLiveData<String>()
    val rate = MutableLiveData<Double>()



    init {
        getMoviesList()
    }

    private fun getMoviesList() {
        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING

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
    fun showList(){
        getMoviesList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun showDetails(position: Int){
        val item = movies.value?.get(position)
        title.value = item?.title
        overview.value = item?.overview
        poster.value = item?.posterPath
        releaseDate.value = item?.releaseDate
        rate.value = item?.voteAverage
        getYear(releaseDate.value)
    }

    fun search(movieName :String ){
        val nameMovie = movies.value?.find { it?.title?.toLowerCase() == movieName.toLowerCase() }
        val searchList = mutableListOf<ResultsItem?>()
       searchList.add(nameMovie)
        _movies.value = searchList

    }
    fun sortListAlpha(){
        val sortedList = movies.value?.sortedBy { it?.title?.toLowerCase() }
        _movies.value = sortedList
    }
    fun sortListRate(){
        val sortedList = movies.value?.sortedBy { it?.voteAverage!! }
        _movies.value = sortedList
    }
    fun sortListReleaseDate(){
        val sortedList = movies.value?.sortedBy { SimpleDateFormat("yyyy-MM-dd").parse(it?.releaseDate!!)}
        _movies.value = sortedList
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getYear(release: String?){
        val date = LocalDate.parse(release)
        releaseYear.value = date.year.toString()
    }

}
package com.example.movies.network

import com.example.movies.data.Movie
import com.example.movies.data.Response
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://api.themoviedb.org"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MoviesApiService {


    @GET("/3/movie/popular?api_key=a06d47a0011d67e6a6c69f40321a686c")
    suspend fun getMovies(@Query("page")pageNo:Int): Response


    @GET("/3/movie/popular?api_key=a06d47a0011d67e6a6c69f40321a686c")
    suspend fun getMoviese(@Query("with_genres")genreId:Int): Response
//    @GET("popular?api_key=a06d47a0011d67e6a6c69f40321a686c")
//    suspend fun getMovieByGenre(@Query("page")pageNo:Int,@Query("with_genre")genreId:Int): Response
}

object MoviesApi {
    val retrofitService : MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java) }
}


enum class MovieApiFilter(val genre:Int){
    ACTION (28),
    ADVENTURE (12),
    ANIMATION (16),
    Drama(18)

}


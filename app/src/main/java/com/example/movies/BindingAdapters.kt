package com.example.movies

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.bumptech.glide.Glide
import com.example.movies.adapter.MoviesAdapter
import com.example.movies.data.ResultsItem

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView , data:List<ResultsItem>?){
    val adapter = recyclerView.adapter as MoviesAdapter
    adapter.submitList(data)
}

@BindingAdapter("poster")
fun bindImage(imgView:ImageView , imgUrl:String){

    imgUrl?.let {
//        val imageLoader = ImageLoader.Builder(imgView.context)
//            .componentRegistry { add(SvgDecoder) }




        val imgUri = imgUrl.toUri().buildUpon().build()
        Log.e("TAG","uri:${imgUri}")
        Glide.with(imgView)

            .load("https://image.tmdb.org/t/p/w500${imgUri}").
            placeholder(R.drawable.loading_animation)
            . error(R.drawable.ic_broken_image)
            .into(imgView)
        Log.e("TAG","uri:${imgUri}")


//        imgView.load("https://image.tmdb.org/t/p/w500"+imgUri){
//            placeholder(R.drawable.loading_animation)
//            error(R.drawable.ic_broken_image)
        }

    }



@BindingAdapter("moviesApiStatus")

fun bindStatus(statusImageView: ImageView , status:MoviesApiStatus){

    when(status){
        MoviesApiStatus.LOADING ->{
            statusImageView.visibility=View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MoviesApiStatus.ERROR -> {
            statusImageView.visibility=View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MoviesApiStatus.DONE ->
            statusImageView.visibility=View.GONE
        }



}
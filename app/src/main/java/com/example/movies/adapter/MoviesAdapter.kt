package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.MoviesListFragment
import com.example.movies.MoviesListFragmentDirections
import com.example.movies.R
import com.example.movies.data.ResultsItem
import com.example.movies.databinding.GridViewItemBinding

class MoviesAdapter : ListAdapter<ResultsItem,MoviesAdapter.MovieViewHolder>(DiffCallback) {

    class MovieViewHolder(private var binding:GridViewItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(resultsItem:ResultsItem){
            binding.movie = resultsItem
            binding.executePendingBindings()

        }
        val card  = binding.movieCard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = getItem(position)
        holder.bind(movieItem)
        holder.card.setOnClickListener {
            val action = MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(position)
            holder.card.findNavController().navigate(action)
        }

    }
    companion object DiffCallback : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
         return   oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.posterPath == newItem.posterPath
        }
    }

}
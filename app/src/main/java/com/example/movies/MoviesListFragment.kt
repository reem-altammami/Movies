package com.example.movies

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movies.adapter.MoviesAdapter
import com.example.movies.databinding.FragmentMoviesListBinding
import java.util.*


class MoviesListFragment : Fragment() {
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel : MoviesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)


        binding.lifecycleOwner = this
        binding.viewModel = sharedViewModel
        binding.listFragment = this
        binding.gridRecyclerView.adapter = MoviesAdapter()

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//        binding.search.setOnCloseListener(object : SearchView.OnCloseListener{
//            override fun onClose(): Boolean {
//                sharedViewModel.showList()
//            return false}
//        })
        binding.search.setOnQueryTextListener (object : SearchView.OnQueryTextListener {


            override fun onQueryTextSubmit(query: String): Boolean {
                if (query != null) {
                    val b =
                        sharedViewModel.movies.value?.any { it?.title?.toLowerCase() == query.toLowerCase() }
                    if (b == true) {
                        sharedViewModel.search(query)
                    } else {
                        val toast =
                            Toast.makeText(requireContext(), "Movie Not Found", Toast.LENGTH_LONG)
                        toast.show()
                    }
                }
                    return true
                }

            override fun onQueryTextChange(newText: String): Boolean {
                sharedViewModel.showList()
                return true
            }

        })

    }

}


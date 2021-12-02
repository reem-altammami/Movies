package com.example.movies

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movies.adapter.MoviesAdapter
import com.example.movies.data.ResultsItem
import com.example.movies.databinding.FragmentMoviesListBinding
import com.example.movies.network.MovieApiFilter
import java.text.SimpleDateFormat
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
        setHasOptionsMenu(true)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.sortList.setOnClickListener { showSortPopupMenu(binding.sortList) }
        binding.filterList.setOnClickListener { showFilterPopupMenu(binding.filterList) }
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
//

    private fun showSortPopupMenu(view: View) {
        val popup = PopupMenu(this.requireContext(), view)
        popup.inflate(R.menu.sort_menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {

                R.id.sort_alpha -> {
                    sharedViewModel.sortListAlpha()
                }
                R.id.release_date-> {
                    sharedViewModel.sortListReleaseDate()
                }
                R.id.rate-> {
                   sharedViewModel.sortListRate()
                }

                R.id.un_sorted -> {
                   sharedViewModel.showList()
                }

            }

            true
        })

        popup.show()
    }
    private fun showFilterPopupMenu(view: View) {
        val popup = PopupMenu(this.requireContext(), view)
        popup.inflate(R.menu.filter_menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {

                R.id.filter_action -> {
                    sharedViewModel.updateFilter(MovieApiFilter.ACTION)
                }
                R.id.filter_animation-> {
                    sharedViewModel.updateFilter(MovieApiFilter.ANIMATION)
                }
                R.id.filter_adventure-> {
                    sharedViewModel.updateFilter(MovieApiFilter.ADVENTURE)
                }

                R.id.show_all-> {
                    sharedViewModel.getMoviesList()
                }

            }

            true
        })

        popup.show()
    }


//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.filter_menu , menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        sharedViewModel.updateFilter(
//            when(item.itemId){
//                R.id.filter_action -> MovieApiFilter.ACTION
//                R.id.filter_animation -> MovieApiFilter.ANIMATION
//                Log.e("TAGB","animation:${MovieApiFilter.ANIMATION}"),
//                R.id.filter_drama -> MovieApiFilter.Drama
//                else ->MovieApiFilter.ADVENTURE
//            }
//        )
//      return  true
//    }

}


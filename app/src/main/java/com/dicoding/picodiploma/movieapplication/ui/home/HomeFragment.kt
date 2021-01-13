package com.dicoding.picodiploma.movieapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.movieapplication.databinding.FragmentHomeBinding
import com.dicoding.picodiploma.movieapplication.ui.movie.MovieAdapter
import com.dicoding.picodiploma.movieapplication.ui.tvseries.TVSeriesAdapter
import com.dicoding.picodiploma.movieapplication.utils.DataDummy

class HomeFragment : Fragment() {

    companion object{
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(index: Int) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }

    }

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            when(arguments?.getInt(ARG_SECTION_NUMBER, 0)){
                1 -> { // Show movie list
                    val movies = DataDummy.generateDummyMovies()
                    val movieAdapter = MovieAdapter()
                    movieAdapter.setMovies(movies)

                    with(fragmentHomeBinding.rvList){
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = movieAdapter
                    }
                }

                2 -> { // Show tv series list
                    val tvSeries = DataDummy.generateDummyTVSeries()
                    val tvSeriesAdapter = TVSeriesAdapter()
                    tvSeriesAdapter.setTVSeries(tvSeries)

                    with(fragmentHomeBinding.rvList){
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = tvSeriesAdapter
                    }
                }
            }
        }
    }
}
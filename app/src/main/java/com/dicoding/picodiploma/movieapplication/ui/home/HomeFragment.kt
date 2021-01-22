package com.dicoding.picodiploma.movieapplication.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.movieapplication.data.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.databinding.FragmentHomeBinding
import com.dicoding.picodiploma.movieapplication.ui.detail.DetailActivity
import com.dicoding.picodiploma.movieapplication.ui.movie.MovieAdapter
import com.dicoding.picodiploma.movieapplication.ui.tvseries.TVSeriesAdapter

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
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]

            when(arguments?.getInt(ARG_SECTION_NUMBER, 0)){
                1 -> { // Show movie list
                    val movies = viewModel.getMovies()
                    val movieAdapter = MovieAdapter()
                    movieAdapter.setMovies(movies)

                    with(fragmentHomeBinding.rvList){
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = movieAdapter
                    }

                    // On click listener
                    movieAdapter.setOnItemClickCallback(object: MovieAdapter.OnItemClickCallback{
                        override fun onItemClicked(movie: MovieEntity) {
                            val intent = Intent(activity, DetailActivity::class.java)
                            intent.putExtra(DetailActivity.EXTRA_MOVIE, movie.movieId)
                            intent.putExtra(DetailActivity.EXTRA_ID, DetailActivity.MOVIE_ID)
                            startActivity(intent)
                        }
                    })
                }

                2 -> { // Show tv series list
                    val tvSeries = viewModel.getTVSeries()
                    val tvSeriesAdapter = TVSeriesAdapter()
                    tvSeriesAdapter.setTVSeries(tvSeries)

                    with(fragmentHomeBinding.rvList){
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = tvSeriesAdapter
                    }

                    tvSeriesAdapter.setOnItemClickCallback(object: TVSeriesAdapter.OnItemClickCallback{
                        override fun onItemClicked(tvSeries: TVSeriesEntity) {
                            val intent = Intent(activity, DetailActivity::class.java)
                            intent.putExtra(DetailActivity.EXTRA_TV_SERIES, tvSeries.tvSeriesId)
                            intent.putExtra(DetailActivity.EXTRA_ID, DetailActivity.TV_SERIES_ID)
                            startActivity(intent)
                        }

                    })
                }
            }
        }
    }
}
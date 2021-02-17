package com.dicoding.picodiploma.movieapplication.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.databinding.FragmentMovieBinding
import com.dicoding.picodiploma.movieapplication.ui.detail.DetailActivity
import com.dicoding.picodiploma.movieapplication.utils.SortUtils
import com.dicoding.picodiploma.movieapplication.valueobject.Status.*
import com.dicoding.picodiploma.movieapplication.viewmodel.ViewModelFactory
import java.util.*

class MovieFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            setSpinner()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?,
                                position: Int, id: Long) {
        when(parent.getItemAtPosition(position).toString().toLowerCase(Locale.getDefault())){
            parent.resources.getString(R.string.name).toLowerCase(Locale.getDefault()) -> { // Sort by name
                showMovieList(SortUtils.NAME)
            }

            parent.resources.getString(R.string.rating).toLowerCase(Locale.getDefault()) -> {  // Sort by rating
                showMovieList(SortUtils.RATING)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    /**
     * Set spinner resources
     */
    private fun setSpinner(){
        val spinner: Spinner = fragmentMovieBinding.sort.sortDropdown
        ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.sort_array,
                R.layout.support_simple_spinner_dropdown_item
        ).also {adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = this
    }

    /**
     * Show movie list in fragment
     */
    private fun showMovieList(sortBy: String){
        val movieAdapter = MovieAdapter()

        // Observe movies
        viewModel.getMovies(sortBy).observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies.status) {
                    LOADING -> setLoading(true)

                    SUCCESS -> {
                        setLoading(false)
                        movieAdapter.submitList(movies.data)
                        movieAdapter.notifyDataSetChanged()
                    }

                    ERROR -> {
                        setLoading(false)
                        Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(fragmentMovieBinding.rvList){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        // On click listener
        movieAdapter.setOnItemClickCallback(object: MovieAdapter.OnItemClickCallback{
            override fun onItemClicked(movie: MovieEntity) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movie.id)
                intent.putExtra(DetailActivity.EXTRA_ID, DetailActivity.MOVIE_ID)
                startActivity(intent)
            }
        })
    }

    /**
     * Set visibility of progressBar
     */
    private fun setLoading(isVisible: Boolean){
        if(isVisible)
            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
        else
            fragmentMovieBinding.progressBar.visibility = View.GONE
    }
}
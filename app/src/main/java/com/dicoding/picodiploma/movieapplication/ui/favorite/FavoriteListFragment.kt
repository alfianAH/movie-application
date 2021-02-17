package com.dicoding.picodiploma.movieapplication.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.databinding.FragmentFavoriteListBinding
import com.dicoding.picodiploma.movieapplication.ui.detail.DetailActivity
import com.dicoding.picodiploma.movieapplication.ui.movie.MovieAdapter
import com.dicoding.picodiploma.movieapplication.ui.tvseries.TVSeriesAdapter
import com.dicoding.picodiploma.movieapplication.utils.SortUtils
import com.dicoding.picodiploma.movieapplication.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.util.*

class FavoriteListFragment : Fragment(), AdapterView.OnItemSelectedListener {

    companion object{
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(index: Int) =
            FavoriteListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }
    }

    private lateinit var fragmentFavoriteListBinding: FragmentFavoriteListBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var tvSeriesAdapter: TVSeriesAdapter
    private lateinit var viewModel: FavoriteListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFavoriteListBinding =
            FragmentFavoriteListBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return fragmentFavoriteListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set touch helper to recycler view
        itemTouchHelper.attachToRecyclerView(fragmentFavoriteListBinding.rvList)

        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteListViewModel::class.java]
            setSpinner()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?,
                                position: Int, id: Long) {
        // Set list on resume
        when(arguments?.getInt(ARG_SECTION_NUMBER, 0)){
            1 -> { // Show movie list
                setList(parent, position, ::showMovieList)
            }

            2 -> { // Show tv series list
                setList(parent, position, ::showTVSeriesList)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    override fun onResume() {
        super.onResume()

        // Set list on resume
        when(arguments?.getInt(ARG_SECTION_NUMBER, 0)){
            1 -> { // Show movie list
                showMovieList(SortUtils.NAME)
            }

            2 -> { // Show tv series list
                showTVSeriesList(SortUtils.NAME)
            }
        }
    }

    /**
     * Set spinner resources
     */
    private fun setSpinner(){
        val spinner: Spinner = fragmentFavoriteListBinding.sort.sortDropdown
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
     * Set sorted favorite list
     */
    private fun setList(parent: AdapterView<*>, position: Int,
                        list: (sortBy: String) -> Unit){
        when(parent.getItemAtPosition(position).toString().toLowerCase(Locale.getDefault())){
            parent.resources.getString(R.string.name).toLowerCase(Locale.getDefault()) -> { // Sort by name
                list(SortUtils.NAME)
            }

            parent.resources.getString(R.string.rating).toLowerCase(Locale.getDefault()) -> {  // Sort by rating
                list(SortUtils.RATING)
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.Callback(){
        // Get touch movement. True when to the left or right
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if(view != null){
                val swipedPosition = viewHolder.adapterPosition

                // Set entity that get swiped
                when(arguments?.getInt(ARG_SECTION_NUMBER, 0)){
                    1 -> { // When to the movie list
                        val movieEntity = movieAdapter.getSwipedData(swipedPosition)
                        movieEntity?.let { viewModel.setFavorite(it) }
                        makeSnackbar(movieEntity)
                    }

                    2 -> { // When to the tv series list
                        val tvSeriesEntity = tvSeriesAdapter.getSwipedData(swipedPosition)
                        tvSeriesEntity?.let { viewModel.setFavorite(it) }
                        makeSnackbar(tvSeriesEntity)
                    }
                }
            }
        }

    })

    /**
     * Show favorite movie list in fragment
     */
    private fun showMovieList(sortBy: String){
        setLoading(true)

        // Observe movies
        viewModel.getMovies(sortBy).observe(viewLifecycleOwner, { movies ->
            setLoading(false)
            movieAdapter = MovieAdapter()
            movieAdapter.submitList(movies)
            movieAdapter.notifyDataSetChanged()

            with(fragmentFavoriteListBinding.rvList){
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
        })
    }

    /**
     * Show favorite tvSeries list in fragment
     */
    private fun showTVSeriesList(sortBy: String){
        setLoading(true)

        viewModel.getTVSeries(sortBy).observe(viewLifecycleOwner, {tvSeries ->
            setLoading(false)
            tvSeriesAdapter = TVSeriesAdapter()
            tvSeriesAdapter.submitList(tvSeries)
            tvSeriesAdapter.notifyDataSetChanged()

            with(fragmentFavoriteListBinding.rvList){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvSeriesAdapter
            }

            tvSeriesAdapter.setOnItemClickCallback(object: TVSeriesAdapter.OnItemClickCallback{
                override fun onItemClicked(tvSeries: TVSeriesEntity) {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TV_SERIES, tvSeries.id)
                    intent.putExtra(DetailActivity.EXTRA_ID, DetailActivity.TV_SERIES_ID)
                    startActivity(intent)
                }
            })

        })
    }

    /**
     * Make undo delete snackbar for movie
     */
    private fun makeSnackbar(movieEntity: MovieEntity?){
        Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG).apply {
            setAction(R.string.message_ok){
                movieEntity?.let { viewModel.setFavorite(it) }
            }
            show()
        }
    }
    
    /**
     * Make undo delete snackbar for tv series
     */
    private fun makeSnackbar(tvSeriesEntity: TVSeriesEntity?){
        Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG).apply {
            setAction(R.string.message_ok){
                tvSeriesEntity?.let { viewModel.setFavorite(it) }
            }
            show()
        }
    }

    /**
     * Set visibility of progressBar
     */
    private fun setLoading(isVisible: Boolean){
        if(isVisible)
            fragmentFavoriteListBinding.progressBar.visibility = View.VISIBLE
        else
            fragmentFavoriteListBinding.progressBar.visibility = View.GONE
    }
}
package com.dicoding.picodiploma.movieapplication.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesGenreEntity
import com.dicoding.picodiploma.movieapplication.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.movieapplication.databinding.ContentDetailMovieBinding
import com.dicoding.picodiploma.movieapplication.ui.movie.MovieGenreAdapter
import com.dicoding.picodiploma.movieapplication.ui.tvseries.TVSeriesGenreAdapter
import com.dicoding.picodiploma.movieapplication.viewmodel.ViewModelFactory
import com.dicoding.picodiploma.movieapplication.utils.ConvertDate
import com.dicoding.picodiploma.movieapplication.valueobject.Resource
import com.dicoding.picodiploma.movieapplication.valueobject.Status.*

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SERIES = "extra_tv_series"
        const val MOVIE_ID = 0
        const val TV_SERIES_ID = 1

        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private lateinit var activityDetailMovieBinding: ActivityDetailBinding
    private lateinit var detailContentBinding: ContentDetailMovieBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailMovieBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailMovieBinding.detailContent

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        setContentView(activityDetailMovieBinding.root)
        setSupportActionBar(activityDetailMovieBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if(extras != null){
            when(extras.getInt(EXTRA_ID)){
                MOVIE_ID -> { // Movie
                    viewModel.setMovieId(extras.getInt(EXTRA_MOVIE))
                    populateMovie()
                }

                TV_SERIES_ID -> { // TV Series
                    viewModel.setTVSeriesId(extras.getInt(EXTRA_TV_SERIES))
                    populateTVSeries()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    /**
     * Set UI for movie entity
     */
    private fun populateMovie(){
        // Set genres
        viewModel.movieGenres.observe(this, { movieGenres ->
            if (movieGenres != null) {
                when (movieGenres.status) {
                    LOADING -> setLoading(true)
                    SUCCESS -> {
                        setLoading(false)
                        setGenreRecyclerView(movieGenres.data as List<MovieGenreEntity>)
                    }

                    ERROR -> {
                        setLoading(true)
                        Toast.makeText(applicationContext, "Something wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        // Observe movie
        viewModel.movieDetail.observe(this, { movie ->

            if (movie != null) {
                when (movie.status) {
                    LOADING -> setLoading(true)

                    SUCCESS -> {
                        val movieEntity = movie.data?.movieEntity
                        setLoading(false)
                        // Set click listener on fab favorite
                        favoriteButtonClickListener(movie)
                        supportActionBar?.title = movieEntity?.title

                        // Set UI
                        detailContentBinding.textTitle.text = movieEntity?.title
//                        detailContentBinding.textStatus.text = getString(R.string.status, movieEntity.status)
                        detailContentBinding.textScore.text = getString(R.string.score, movieEntity?.voteAverage)
                        detailContentBinding.textReleaseDate.text = getString(R.string.release_date,
                                ConvertDate.convertStringToDate(movieEntity?.releaseDate as String))
                        detailContentBinding.textSummary.text = movieEntity.overview

                        // Set poster
                        Glide.with(this)
                                .load(IMAGE_URL + movieEntity.posterPath)
                                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                                .error(R.drawable.ic_error)
                                .into(detailContentBinding.imagePoster)
                    }

                    ERROR -> {
                        setLoading(true)
                        Toast.makeText(applicationContext, "Something wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    /**
     * Set UI for tv series entity
     */
    private fun populateTVSeries(){
        // Set genres
        viewModel.tvSeriesGenres.observe(this, { tvSeriesGenre ->
            if (tvSeriesGenre != null) {
                when (tvSeriesGenre.status) {
                    LOADING -> setLoading(true)
                    SUCCESS -> {
                        setLoading(false)
                        setGenreRecyclerView(tvSeriesGenre.data as List<TVSeriesGenreEntity>)
                    }

                    ERROR -> {
                        setLoading(true)
                        Toast.makeText(applicationContext, "Something wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        // Observe tv series
        viewModel.tvSeriesDetail.observe(this, { tvSeries ->

            if (tvSeries != null) {
                when (tvSeries.status) {
                    LOADING -> setLoading(true)

                    SUCCESS -> {
                        val tvSeriesEntity = tvSeries.data?.tvSeriesEntity
                        setLoading(false)
                        // Set click listener on fab favorite
                        favoriteButtonClickListener(tvSeries)
                        supportActionBar?.title = tvSeriesEntity?.title

                        // Set UI
                        detailContentBinding.textTitle.text = tvSeriesEntity?.title
//                        detailContentBinding.textStatus.text = getString(R.string.status, movieEntity.status)
                        detailContentBinding.textScore.text = getString(R.string.score, tvSeriesEntity?.voteAverage)
                        detailContentBinding.textReleaseDate.text = getString(R.string.release_date,
                                ConvertDate.convertStringToDate(tvSeriesEntity?.releaseDate as String))
                        detailContentBinding.textSummary.text = tvSeriesEntity.overview

                        // Set poster
                        Glide.with(this)
                                .load(IMAGE_URL + tvSeriesEntity.posterPath)
                                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                                .error(R.drawable.ic_error)
                                .into(detailContentBinding.imagePoster)
                    }

                    ERROR -> {
                        setLoading(true)
                        Toast.makeText(applicationContext, "Something wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    /**
     * Set movie genre recycler view horizontally
     */
    @JvmName("setGenreRecyclerView1")
    private fun setGenreRecyclerView(genres: List<MovieGenreEntity>?){
        // Set genre
        val genreAdapter = MovieGenreAdapter()
        genreAdapter.setGenres(genres)
        genreAdapter.notifyDataSetChanged()

        with(detailContentBinding.rvGenreList){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = genreAdapter
        }
    }

    /**
     * Set movie genre recycler view horizontally
     */
    private fun setGenreRecyclerView(genres: List<TVSeriesGenreEntity>?){
        // Set genre
        val genreAdapter = TVSeriesGenreAdapter()
        genreAdapter.setGenres(genres)
        genreAdapter.notifyDataSetChanged()

        with(detailContentBinding.rvGenreList){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = genreAdapter
        }
    }

    /**
     * Set image resource of fab favorite
     */
    private fun setFavoriteStatus(status: Boolean){
        if(status){
            activityDetailMovieBinding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else{
            activityDetailMovieBinding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    /**
     * Favorite Button click listener for movie
     */
    @JvmName("favoriteButtonClickListener1")
    private fun favoriteButtonClickListener(movieDetail: Resource<MovieDetails>){
        val favoriteStatus = movieDetail.data?.movieEntity?.isFavorite as Boolean

        setFavoriteStatus(favoriteStatus)

        activityDetailMovieBinding.fabFavorite.setOnClickListener {
            favoriteStatus != favoriteStatus
            setFavoriteStatus(favoriteStatus)

            viewModel.setFavorite(movieDetail)
        }
    }

    /**
     * Favorite Button click listener for TVSeries
     */
    private fun favoriteButtonClickListener(tvSeriesDetail: Resource<TVSeriesDetails>){
        val favoriteStatus = tvSeriesDetail.data?.tvSeriesEntity?.isFavorite as Boolean

        setFavoriteStatus(favoriteStatus)

        activityDetailMovieBinding.fabFavorite.setOnClickListener {
            favoriteStatus != favoriteStatus
            setFavoriteStatus(favoriteStatus)

            viewModel.setFavorite(tvSeriesDetail)
        }
    }

    /**
     * Set visibility of progressBar
     */
    private fun setLoading(isVisible: Boolean){
        if(isVisible)
            detailContentBinding.progressBar.visibility = View.VISIBLE
        else
            detailContentBinding.progressBar.visibility = View.GONE
    }
}
package com.dicoding.picodiploma.movieapplication.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.data.GenresItem
import com.dicoding.picodiploma.movieapplication.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.movieapplication.databinding.ContentDetailMovieBinding
import com.dicoding.picodiploma.movieapplication.ui.genre.GenreAdapter
import com.dicoding.picodiploma.movieapplication.utils.ConvertDate

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SERIES = "extra_tv_series"
        const val MOVIE_ID = 0
        const val TV_SERIES_ID = 1

        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private lateinit var detailContentBinding: ContentDetailMovieBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailMovieBinding.detailContent

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

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
        viewModel.findDetailMovie()

        // Observe movie
        viewModel.detailMovie.observe(this, {movie ->
            supportActionBar?.title = movie.title

            // Set UI
            detailContentBinding.textTitle.text = movie.title
            detailContentBinding.textStatus.text = getString(R.string.status, movie.status)
            detailContentBinding.textScore.text = getString(R.string.score, movie.voteAverage)
            detailContentBinding.textReleaseDate.text = getString(R.string.release_date,
                    ConvertDate.convertStringToDate(movie.releaseDate))
            detailContentBinding.textSummary.text = movie.overview

            // Set genre
            setGenreRecyclerView(movie.genres)

            // Set poster
            Glide.with(this)
                    .load(IMAGE_URL + movie.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(detailContentBinding.imagePoster)

        })

        setLoading()
    }

    /**
     * Set UI for tv series entity
     */
    private fun populateTVSeries(){

        viewModel.findDetailTVSeries()

        // Observe movie
        viewModel.detailTvSeries.observe(this, {tvSeries ->
            supportActionBar?.title = tvSeries.name

            // Set UI
            detailContentBinding.textTitle.text = tvSeries.name
            detailContentBinding.textStatus.text = getString(R.string.status, tvSeries.status)
            detailContentBinding.textScore.text = getString(R.string.score, tvSeries.voteAverage)
            detailContentBinding.textReleaseDate.text = getString(R.string.release_date,
                    ConvertDate.convertStringToDate(tvSeries.firstAirDate))
            detailContentBinding.textSummary.text = tvSeries.overview

            // Set genre
            setGenreRecyclerView(tvSeries.genres)

            // Set photo
            Glide.with(this)
                    .load(IMAGE_URL + tvSeries.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(detailContentBinding.imagePoster)
        })

        setLoading()
    }

    /**
     * Set genre recycler view horizontally
     */
    private fun setGenreRecyclerView(genres: List<GenresItem>){
        // Set genre
        val genreAdapter = GenreAdapter()
        genreAdapter.setGenres(genres)

        with(detailContentBinding.rvGenreList){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = genreAdapter
        }
    }

    /**
     * Set loading
     */
    private fun setLoading(){
        // Observe isLoading
        viewModel.isLoading.observe(this, {
            detailContentBinding.progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })
    }
}
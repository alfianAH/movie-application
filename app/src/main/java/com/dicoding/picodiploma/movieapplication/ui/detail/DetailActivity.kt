package com.dicoding.picodiploma.movieapplication.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.data.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.movieapplication.databinding.ContentDetailMovieBinding

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SERIES = "extra_tv_series"

        private const val MOVIE_ID = 0
        private const val TV_SERIES_ID = 1
    }

    private lateinit var detailContentBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailMovieBinding.detailContent

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        setContentView(activityDetailMovieBinding.root)
        setSupportActionBar(activityDetailMovieBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if(extras != null){
            when(extras.getInt(EXTRA_ID)){
                MOVIE_ID -> { // Movie
                    viewModel.setSelectedMovie(extras.getInt(EXTRA_MOVIE))
                    populateMovie(viewModel.getMovie())
                }

                TV_SERIES_ID -> { // TV Series
                    viewModel.setSelectedTVSeries(extras.getInt(EXTRA_TV_SERIES))
                    populateTVSeries(viewModel.getTVSeries())
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
    private fun populateMovie(movieEntity: MovieEntity){
        supportActionBar?.title = movieEntity.title
        detailContentBinding.textTitle.text = movieEntity.title
        detailContentBinding.textReleaseYear.text = movieEntity.releaseYear.toString()
        detailContentBinding.textGenre.text = movieEntity.genre
        detailContentBinding.textDirectors.text = movieEntity.director
        detailContentBinding.textSummary.text = movieEntity.summary

        Glide.with(this)
            .load(movieEntity.imagePath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(detailContentBinding.imagePoster)
    }

    /**
     * Set UI for tv series entity
     */
    private fun populateTVSeries(tvSeriesEntity: TVSeriesEntity){
        supportActionBar?.title = tvSeriesEntity.title
        detailContentBinding.textTitle.text = tvSeriesEntity.title
        detailContentBinding.textReleaseYear.text = tvSeriesEntity.releaseYear.toString()
        detailContentBinding.textGenre.text = tvSeriesEntity.genre
        detailContentBinding.textDirectors.text = tvSeriesEntity.creator
        detailContentBinding.textSummary.text = tvSeriesEntity.summary

        Glide.with(this)
            .load(tvSeriesEntity.imagePath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(detailContentBinding.imagePoster)
    }
}
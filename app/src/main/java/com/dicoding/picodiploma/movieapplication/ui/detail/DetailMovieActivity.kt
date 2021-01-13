package com.dicoding.picodiploma.movieapplication.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.data.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.databinding.ActivityDetailMovieBinding
import com.dicoding.picodiploma.movieapplication.databinding.ContentDetailMovieBinding
import com.dicoding.picodiploma.movieapplication.utils.DataDummy

class DetailMovieActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SERIES = "extra_tv_series"
    }

    private lateinit var detailContentBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)
        setSupportActionBar(activityDetailMovieBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if(extras != null){
            when(extras.getInt(EXTRA_ID)){
                0 -> { // Movie
                    val movieId = extras.getInt(EXTRA_MOVIE)
                    for(movie in DataDummy.generateDummyMovies()){
                        if(movie.movieId == movieId){
                            populateMovie(movie)
                        }
                    }
                }

                1 -> { // TV Series
                    val tvSeriesId = extras.getInt(EXTRA_TV_SERIES)
                    for(tvSeries in DataDummy.generateDummyTVSeries()){
                        if(tvSeries.tvSeriesId == tvSeriesId){
                            populateTVSeries(tvSeries)
                        }
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

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
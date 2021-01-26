package com.dicoding.picodiploma.movieapplication.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.movieapplication.BuildConfig
import com.dicoding.picodiploma.movieapplication.data.source.remote.MovieAppRepository

class DetailViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {
    private var movieId: Int = 0
    private var tvSeriesId: Int = 0

    companion object{
        private const val API_KEY = BuildConfig.TMBDApiKey
    }

    fun setMovieId(movieId: Int){
        this.movieId = movieId
    }

    fun setTVSeriesId(tvSeriesId: Int){
        this.tvSeriesId = tvSeriesId
    }

    fun getDetailMovie() = movieAppRepository.getDetailMovie(API_KEY, movieId)

    fun getDetailTVSeries() = movieAppRepository.getDetailTVSeries(API_KEY, tvSeriesId)
}
package com.dicoding.picodiploma.movieapplication.ui.tvseries

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.movieapplication.BuildConfig
import com.dicoding.picodiploma.movieapplication.data.source.MovieAppRepository

class TVSeriesViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    companion object{
        private const val API_KEY = BuildConfig.TMBDApiKey
    }

    fun getTVSeries() = movieAppRepository.getTVSeries(API_KEY)
}
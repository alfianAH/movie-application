package com.dicoding.picodiploma.movieapplication.ui.home

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.movieapplication.BuildConfig
import com.dicoding.picodiploma.movieapplication.data.source.MovieAppRepository

class HomeViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    companion object{
        private const val API_KEY = BuildConfig.TMBDApiKey
    }

    fun getMovies() = movieAppRepository.getMovies(API_KEY)

    fun getTVSeries() = movieAppRepository.getTVSeries(API_KEY)
}
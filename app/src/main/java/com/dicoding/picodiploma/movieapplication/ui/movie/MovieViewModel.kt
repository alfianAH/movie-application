package com.dicoding.picodiploma.movieapplication.ui.movie

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.movieapplication.BuildConfig
import com.dicoding.picodiploma.movieapplication.data.source.MovieAppRepository

class MovieViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    companion object{
        private const val API_KEY = BuildConfig.TMBDApiKey
    }

    fun getMovies() = movieAppRepository.getMovies(API_KEY)
}
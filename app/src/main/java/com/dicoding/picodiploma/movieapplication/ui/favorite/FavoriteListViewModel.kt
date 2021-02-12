package com.dicoding.picodiploma.movieapplication.ui.favorite

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.movieapplication.BuildConfig
import com.dicoding.picodiploma.movieapplication.data.MovieAppRepository

class FavoriteListViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    fun getMovies() = movieAppRepository.getMovies()

    fun getTVSeries() = movieAppRepository.getTVSeries()
}
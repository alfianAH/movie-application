package com.dicoding.picodiploma.movieapplication.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.movieapplication.data.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.valueobject.Resource

class MovieViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    fun getMovies(): LiveData<Resource<List<MovieEntity>>> =
            movieAppRepository.getMovies()
}
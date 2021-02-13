package com.dicoding.picodiploma.movieapplication.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.picodiploma.movieapplication.data.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity

class FavoriteListViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    fun getMovies(): LiveData<PagedList<MovieEntity>> = movieAppRepository.getFavoriteMovies()

    fun getTVSeries(): LiveData<PagedList<TVSeriesEntity>> = movieAppRepository.getFavoriteTVSeries()
}
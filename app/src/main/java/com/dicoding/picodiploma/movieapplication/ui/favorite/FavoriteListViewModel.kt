package com.dicoding.picodiploma.movieapplication.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.picodiploma.movieapplication.data.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity

class FavoriteListViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    fun getMovies(sortBy: String): LiveData<PagedList<MovieEntity>> =
            movieAppRepository.getFavoriteMovies(sortBy)

    fun getTVSeries(sortBy: String): LiveData<PagedList<TVSeriesEntity>> =
            movieAppRepository.getFavoriteTVSeries(sortBy)

    fun setFavorite(movieEntity: MovieEntity){
        val newState = !movieEntity.isFavorite
        movieAppRepository.setFavorite(movieEntity, newState)
    }

    fun setFavorite(tvSeriesEntity: TVSeriesEntity){
        val newState = !tvSeriesEntity.isFavorite
        movieAppRepository.setFavorite(tvSeriesEntity, newState)
    }
}
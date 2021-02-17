package com.dicoding.picodiploma.movieapplication.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.valueobject.Resource

interface MovieAppDataSource {
    fun getMovies(sortBy: String)             : LiveData<Resource<PagedList<MovieEntity>>>
    fun getTVSeries(sortBy: String)           : LiveData<Resource<PagedList<TVSeriesEntity>>>
    fun getDetailMovie(movieId: Int)          : LiveData<Resource<MovieDetails>>
    fun getDetailTVSeries(tvSeriesId: Int)    : LiveData<Resource<TVSeriesDetails>>
    fun getMovieGenres(movieId: Int)          : LiveData<Resource<List<MovieGenreEntity>>>
    fun getTVSeriesGenres(tvSeriesId: Int)    : LiveData<Resource<List<TVSeriesGenreEntity>>>
    fun getFavoriteMovies(sortBy: String)     : LiveData<PagedList<MovieEntity>>
    fun getFavoriteTVSeries(sortBy: String)   : LiveData<PagedList<TVSeriesEntity>>

    fun setFavorite(movie: MovieEntity, state: Boolean)
    fun setFavorite(tvSeries: TVSeriesEntity, state: Boolean)
}
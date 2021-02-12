package com.dicoding.picodiploma.movieapplication.data

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.valueobject.Resource

interface MovieAppDataSource {
    fun getMovies()                           : LiveData<Resource<List<MovieEntity>>>
    fun getTVSeries()                         : LiveData<Resource<List<TVSeriesEntity>>>
    fun getDetailMovie(movieId: Int)          : LiveData<Resource<MovieDetails>>
    fun getDetailTVSeries(tvSeriesId: Int)    : LiveData<Resource<TVSeriesDetails>>
    fun getMovieGenres(movieId: Int)          : LiveData<Resource<List<MovieGenreEntity>>>
    fun getTVSeriesGenres(tvSeriesId: Int)    : LiveData<Resource<List<TVSeriesGenreEntity>>>
    fun getFavoriteMovies()                   : LiveData<List<MovieEntity>>
    fun getFavoriteTVSeries()                 : LiveData<List<TVSeriesEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)
    fun setFavoriteTVSeries(tvSeries: TVSeriesEntity, state: Boolean)
}
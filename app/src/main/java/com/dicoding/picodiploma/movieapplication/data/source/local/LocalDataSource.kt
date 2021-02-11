package com.dicoding.picodiploma.movieapplication.data.source.local

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetailEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.room.MovieAppDao

class LocalDataSource(private val movieAppDao: MovieAppDao) {
    companion object{
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieAppDao: MovieAppDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieAppDao)
    }

    fun getMovies(): LiveData<List<MovieEntity>> = movieAppDao.getMovies()

    fun getFavoriteMovies(): LiveData<List<MovieEntity>> = movieAppDao.getFavoriteMovies()

    fun getDetailMovieById(movieId: Int): LiveData<MovieDetailEntity> =
        movieAppDao.getDetailMovieById(movieId)

    fun insertMovies(movies: List<MovieEntity>) = movieAppDao.insertMovies(movies)

    fun updateMovie(movie: MovieEntity) = movieAppDao.updateMovie(movie)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean){
        movie.isFavorite = newState
        movieAppDao.updateMovie(movie)
    }

    // TV Series
    fun getTVSeries(): LiveData<List<TVSeriesEntity>> = movieAppDao.getTVSeries()

    fun getFavoriteTVSeries(): LiveData<List<TVSeriesEntity>> = movieAppDao.getFavoriteTVSeries()

    fun getDetailTVSeriesById(tvSeriesId: Int): LiveData<TVSeriesDetailEntity> =
        movieAppDao.getDetailTVSeriesById(tvSeriesId)


    fun insertTVSeries(tvSeries: List<TVSeriesEntity>) = movieAppDao.insertTVSeries(tvSeries)

    fun updateTVSeries(tvSeries: TVSeriesEntity) = movieAppDao.updateTVSeries(tvSeries)

    fun setTVSeriesFavorite(tvSeries: TVSeriesEntity, newState: Boolean){
        tvSeries.isFavorite = newState
        movieAppDao.updateTVSeries(tvSeries)
    }
}
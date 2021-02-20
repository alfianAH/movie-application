package com.dicoding.picodiploma.movieapplication.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.room.MovieAppDao
import com.dicoding.picodiploma.movieapplication.utils.SortUtils

class LocalDataSource(private val movieAppDao: MovieAppDao) {
    companion object{
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieAppDao: MovieAppDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieAppDao)
    }

    fun getMovies(sortBy: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtils.getSortedMovie(sortBy)
        return movieAppDao.getMovies(query)
    }

    fun getFavoriteMovies(sortBy: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtils.getSortedFavMovie(sortBy)
        return movieAppDao.getFavoriteMovies(query)
    }

    fun getDetailMovieById(movieId: Int): LiveData<MovieDetails> =
        movieAppDao.getDetailMovieById(movieId)

    fun insertMovies(movies: List<MovieEntity>) = movieAppDao.insertMovies(movies)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean){
        movie.isFavorite = newState
        movieAppDao.updateMovie(movie)
    }

    fun getMovieGenresById(movieId: Int): LiveData<List<MovieGenreEntity>> =
            movieAppDao.getMovieGenresById(movieId)

    fun insertMovieGenres(genres: List<MovieGenreEntity>) = movieAppDao.insertMovieGenres(genres)

    // TV Series
    fun getTVSeries(sortBy: String): DataSource.Factory<Int, TVSeriesEntity> {
        val query = SortUtils.getSortedTVSeries(sortBy)
        return movieAppDao.getTVSeries(query)
    }

    fun getFavoriteTVSeries(sortBy: String): DataSource.Factory<Int, TVSeriesEntity> {
        val query = SortUtils.getSortedFavTVSeries(sortBy)
        return movieAppDao.getFavoriteTVSeries(query)
    }

    fun getDetailTVSeriesById(tvSeriesId: Int): LiveData<TVSeriesDetails> =
        movieAppDao.getDetailTVSeriesById(tvSeriesId)


    fun insertTVSeries(tvSeries: List<TVSeriesEntity>) = movieAppDao.insertTVSeries(tvSeries)

    fun setTVSeriesFavorite(tvSeries: TVSeriesEntity, newState: Boolean){
        tvSeries.isFavorite = newState
        movieAppDao.updateTVSeries(tvSeries)
    }

    fun getTVSeriesGenresById(tvSeriesId: Int): LiveData<List<TVSeriesGenreEntity>> =
            movieAppDao.getTVSeriesGenresById(tvSeriesId)

    fun insertTVSeriesGenres(genres: List<TVSeriesGenreEntity>) = movieAppDao.insertTVSeriesGenres(genres)
}
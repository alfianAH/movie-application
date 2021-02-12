package com.dicoding.picodiploma.movieapplication.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesGenreEntity

@Dao
interface MovieAppDao{
    // Movie
    @Query("SELECT * FROM movieEntities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieEntities WHERE isFavorite = 1")
    fun getFavoriteMovies(): LiveData<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movieEntities WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int): LiveData<MovieDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM movieGenreEntities WHERE movieId = :movieId")
    fun getMovieGenresById(movieId: Int): LiveData<MovieGenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieGenres(genres: List<MovieGenreEntity>)

    // TV Series
    @Query("SELECT * FROM tvSeriesEntities")
    fun getTVSeries(): LiveData<List<TVSeriesEntity>>

    @Query("SELECT * FROM tvSeriesEntities WHERE isFavorite = 1")
    fun getFavoriteTVSeries(): LiveData<List<TVSeriesEntity>>

    @Transaction
    @Query("SELECT * FROM tvSeriesEntities WHERE tvSeriesId = :tvSeriesId")
    fun getDetailTVSeriesById(tvSeriesId: Int): LiveData<TVSeriesDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVSeries(tvSeries: List<TVSeriesEntity>)

    @Update
    fun updateTVSeries(tvSeries: TVSeriesEntity)

    @Query("SELECT * FROM tvSeriesGenreEntities WHERE tvSeriesId = :tvSeriesId")
    fun getTVSeriesGenresById(tvSeriesId: Int): LiveData<TVSeriesGenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVSeriesGenres(genres: List<TVSeriesGenreEntity>)
}
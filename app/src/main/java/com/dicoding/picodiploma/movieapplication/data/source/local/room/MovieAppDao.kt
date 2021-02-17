package com.dicoding.picodiploma.movieapplication.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesGenreEntity

@Dao
interface MovieAppDao{
    // Movie
    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getFavoriteMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movieEntities WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int): LiveData<MovieDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM movieGenreEntities WHERE movieId = :movieId")
    fun getMovieGenresById(movieId: Int): LiveData<List<MovieGenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieGenres(genres: List<MovieGenreEntity>)

    // TV Series
    @RawQuery(observedEntities = [TVSeriesEntity::class])
    fun getTVSeries(query: SupportSQLiteQuery): DataSource.Factory<Int, TVSeriesEntity>

    @RawQuery(observedEntities = [TVSeriesEntity::class])
    fun getFavoriteTVSeries(query: SupportSQLiteQuery): DataSource.Factory<Int, TVSeriesEntity>

    @Transaction
    @Query("SELECT * FROM tvSeriesEntities WHERE tvSeriesId = :tvSeriesId")
    fun getDetailTVSeriesById(tvSeriesId: Int): LiveData<TVSeriesDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVSeries(tvSeries: List<TVSeriesEntity>)

    @Update
    fun updateTVSeries(tvSeries: TVSeriesEntity)

    @Query("SELECT * FROM tvSeriesGenreEntities WHERE tvSeriesId = :tvSeriesId")
    fun getTVSeriesGenresById(tvSeriesId: Int): LiveData<List<TVSeriesGenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVSeriesGenres(genres: List<TVSeriesGenreEntity>)
}
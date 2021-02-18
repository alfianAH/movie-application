package com.dicoding.picodiploma.movieapplication.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.picodiploma.movieapplication.data.source.local.LocalDataSource
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.movieapplication.utils.*
import com.dicoding.picodiploma.movieapplication.valueobject.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieAppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieAppRepository = FakeMovieAppRepository(remote, localDataSource, appExecutors)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0].id
    private val movieGenresResponse = DataDummy.generateRemoteDummyMovieGenres(movieId)

    private val tvSeriesResponses = DataDummy.generateRemoteDummyTVSeries()
    private val tvSeriesId = tvSeriesResponses[0].id
    private val tvSeriesGenresResponse = DataDummy.generateRemoteDummyTVSeriesGenres(movieId)

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>

        `when`(localDataSource.getMovies(SortUtils.NAME)).thenReturn(dataSourceFactory)
        movieAppRepository.getMovies(SortUtils.NAME)

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))

        verify(localDataSource).getMovies(SortUtils.NAME)
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun getTVSeries() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVSeriesEntity>
        `when`(localDataSource.getTVSeries(SortUtils.NAME)).thenReturn(dataSourceFactory)
        movieAppRepository.getTVSeries(SortUtils.NAME)

        val tvSeriesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTVSeries()))

        verify(localDataSource).getTVSeries(SortUtils.NAME)
        assertNotNull(tvSeriesEntities.data)
        assertEquals(tvSeriesResponses.size, tvSeriesEntities.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyMovie = MutableLiveData<MovieDetails>()
        dummyMovie.value = DataDummy.generateDummyDetailMovie(DataDummy.generateDummyMovies()[0], false)

        `when`(localDataSource.getDetailMovieById(movieId)).thenReturn(dummyMovie)

        val movieEntity = LiveDataTestUtil.getValue(movieAppRepository.getDetailMovie(movieId))

        verify(localDataSource).getDetailMovieById(movieId)
        assertNotNull(movieEntity.data)
        assertNotNull(movieEntity.data?.movieEntity?.title)
        assertEquals(movieResponses[0].title, movieEntity.data?.movieEntity?.title)
    }

    @Test
    fun getDetailTVSeries() {
        val dummyTVSeries = MutableLiveData<TVSeriesDetails>()
        dummyTVSeries.value = DataDummy.generateDummyDetailTVSeries(DataDummy.generateDummyTVSeries()[0], false)

        `when`(localDataSource.getDetailTVSeriesById(tvSeriesId)).thenReturn(dummyTVSeries)

        val tvSeriesEntity = LiveDataTestUtil.getValue(movieAppRepository.getDetailTVSeries(tvSeriesId))

        verify(localDataSource).getDetailTVSeriesById(tvSeriesId)
        assertNotNull(tvSeriesEntity.data)
        assertNotNull(tvSeriesEntity.data?.tvSeriesEntity?.title)
        assertEquals(tvSeriesResponses[0].name, tvSeriesEntity.data?.tvSeriesEntity?.title)
    }

    @Test
    fun getMovieGenres(){
        val dummyGenres = MutableLiveData<List<MovieGenreEntity>>()
        dummyGenres.value = DataDummy.generateDummyMovieGenres(movieId)

        `when`(localDataSource.getMovieGenresById(movieId)).thenReturn(dummyGenres)

        val genreEntities = LiveDataTestUtil.getValue(movieAppRepository.getMovieGenres(movieId))

        verify(localDataSource).getMovieGenresById(movieId)
        assertNotNull(genreEntities.data)
        assertEquals(movieGenresResponse.size, genreEntities.data?.size)
    }

    @Test
    fun getTVSeriesGenres(){
        val dummyGenres = MutableLiveData<List<TVSeriesGenreEntity>>()
        dummyGenres.value = DataDummy.generateDummyTVSeriesGenres(tvSeriesId)

        `when`(localDataSource.getTVSeriesGenresById(tvSeriesId)).thenReturn(dummyGenres)

        val genreEntities = LiveDataTestUtil.getValue(movieAppRepository.getTVSeriesGenres(tvSeriesId))

        verify(localDataSource).getTVSeriesGenresById(tvSeriesId)
        assertNotNull(genreEntities.data)
        assertEquals(tvSeriesGenresResponse.size, genreEntities.data?.size)
    }

    @Test
    fun getFavoriteMovies(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>

        `when`(localDataSource.getFavoriteMovies(SortUtils.NAME)).thenReturn(dataSourceFactory)
        movieAppRepository.getFavoriteMovies(SortUtils.NAME)

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))

        verify(localDataSource).getFavoriteMovies(SortUtils.NAME)
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun getFavoriteTVSeries(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVSeriesEntity>

        `when`(localDataSource.getFavoriteTVSeries(SortUtils.NAME)).thenReturn(dataSourceFactory)
        movieAppRepository.getFavoriteTVSeries(SortUtils.NAME)

        val tvSeriesEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTVSeries()))

        verify(localDataSource).getFavoriteTVSeries(SortUtils.NAME)
        assertNotNull(tvSeriesEntity.data)
        assertEquals(movieResponses.size, tvSeriesEntity.data?.size)
    }
}
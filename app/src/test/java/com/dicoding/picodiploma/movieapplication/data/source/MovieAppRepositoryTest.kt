package com.dicoding.picodiploma.movieapplication.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.picodiploma.movieapplication.BuildConfig
import com.dicoding.picodiploma.movieapplication.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.DetailMovieResponse
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.DetailTVSeriesResponse
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.MovieResultsItem
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.TVSeriesResultsItem
import com.dicoding.picodiploma.movieapplication.utils.APILoaderHelper
import com.dicoding.picodiploma.movieapplication.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito
import retrofit2.Call

class MovieAppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieAppRepository = FakeMovieAppRepository(remote)
    private val apiKey = BuildConfig.TMBDApiKey

    private val movieResponse = APILoaderHelper().findMovies(apiKey)
    private var movieId: Int? = 0
    private val tvSeriesResponse = APILoaderHelper().findTVSeries(apiKey)
    private var tvSeriesId: Int? = 0

    private lateinit var movieEntities: List<MovieResultsItem>
    private lateinit var tvSeriesEntities: List<TVSeriesResultsItem>
    private lateinit var detailMovieResponse: Call<DetailMovieResponse>
    private lateinit var detailTVSeriesResponse: Call<DetailTVSeriesResponse>

    @Before
    fun setUp(){
        movieId = getMovieId()
        tvSeriesId = getTVSeriesId()

        detailMovieResponse = APILoaderHelper().findDetailMovie(apiKey, movieId as Int)
        detailTVSeriesResponse = APILoaderHelper().findDetailTVSeries(apiKey, tvSeriesId as Int)
    }

    private fun getMovieId(): Int{
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMoviesCallback)
                    .onAllMoviesReceived(movieResponse)
            null
        }.`when`(remote).getMovies(eq(apiKey), any())

        movieEntities = LiveDataTestUtil.getValue(movieAppRepository.getMovies(apiKey))
        return movieEntities[0].id
    }

    private fun getTVSeriesId(): Int{
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTVSeriesCallback)
                    .onAllTVSeriesReceived(tvSeriesResponse)
            null
        }.`when`(remote).getTVSeries(eq(apiKey), any())

        tvSeriesEntities = LiveDataTestUtil.getValue(movieAppRepository.getTVSeries(apiKey))
        return tvSeriesEntities[0].id
    }

    @Test
    fun getMovies() {
        verify(remote).getMovies(eq(apiKey), any())
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities.size)
    }

    @Test
    fun getTVSeries() {
        verify(remote).getTVSeries(eq(apiKey), any())
        assertNotNull(tvSeriesEntities)
        assertEquals(20, tvSeriesEntities.size)
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[2] as RemoteDataSource.LoadDetailMovieCallback)
                    .onDetailMovieReceived(detailMovieResponse)
            null
        }.`when`(remote).getDetailMovie(eq(apiKey), eq(movieId as Int), any())

        val detailMovieEntity =
                LiveDataTestUtil.getValue(movieAppRepository.getDetailMovie(apiKey, movieId as Int))

        verify(remote).getDetailMovie(eq(apiKey), eq(movieId as Int), any())
        assertNotNull(detailMovieEntity)
        assertEquals("Wonder Woman 1984", detailMovieEntity.title)
    }

    @Test
    fun getDetailTVSeries() {
        doAnswer { invocation ->
            (invocation.arguments[2] as RemoteDataSource.LoadDetailTVSeriesCallback)
                    .onDetailTVSeriesReceived(detailTVSeriesResponse)
            null
        }.`when`(remote).getDetailTVSeries(eq(apiKey), eq(tvSeriesId as Int), any())

        val detailTVSeriesEntity =
                LiveDataTestUtil.getValue(movieAppRepository.getDetailTVSeries(apiKey, tvSeriesId as Int))

        verify(remote).getDetailTVSeries(eq(apiKey), eq(tvSeriesId as Int), any())
        assertNotNull(detailTVSeriesEntity)
        assertEquals("I Am Not an Animal", detailTVSeriesEntity.name)
    }
}
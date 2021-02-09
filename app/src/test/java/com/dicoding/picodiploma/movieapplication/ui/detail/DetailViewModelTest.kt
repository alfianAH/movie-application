package com.dicoding.picodiploma.movieapplication.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.movieapplication.BuildConfig
import com.dicoding.picodiploma.movieapplication.data.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.DetailMovieResponse
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.DetailTVSeriesResponse
import com.dicoding.picodiploma.movieapplication.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyDetailMovie()
    private val dummyTVSeries = DataDummy.generateDummyDetailTVSeries()
    private val movieId = dummyMovie.id
    private val tvSeriesId = dummyTVSeries.id
    private val apiKey = BuildConfig.TMBDApiKey

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAppRepository: MovieAppRepository

    @Mock
    private lateinit var movieObserver: Observer<DetailMovieResponse>

    @Mock
    private lateinit var tvSeriesObserver: Observer<DetailTVSeriesResponse>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieAppRepository)
        // Set ids
        viewModel.setMovieId(movieId)
        viewModel.setTVSeriesId(tvSeriesId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<DetailMovieResponse>()
        movie.value = dummyMovie

        // Check movie entity
        `when`(movieAppRepository.getDetailMovie(apiKey, movieId)).thenReturn(movie)
        val movieEntity = viewModel.getDetailMovie().value

        verify(movieAppRepository).getDetailMovie(apiKey, movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity?.id)
        assertEquals(dummyMovie.title, movieEntity?.title)
        assertEquals(dummyMovie.releaseDate, movieEntity?.releaseDate)
        assertEquals(dummyMovie.overview, movieEntity?.overview)
        assertEquals(dummyMovie.genres, movieEntity?.genres)
        assertEquals(dummyMovie.status, movieEntity?.status)
        assertEquals(dummyMovie.posterPath, movieEntity?.posterPath)
        assertEquals(dummyMovie.voteAverage, movieEntity?.voteAverage)

        viewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTVSeries() {
        val tvSeries = MutableLiveData<DetailTVSeriesResponse>()
        tvSeries.value = dummyTVSeries

        // Check movie entity
        `when`(movieAppRepository.getDetailTVSeries(apiKey, tvSeriesId)).thenReturn(tvSeries)
        val tvSeriesEntity = viewModel.getDetailTVSeries().value

        verify(movieAppRepository).getDetailTVSeries(apiKey, tvSeriesId)
        assertNotNull(tvSeriesEntity)
        assertEquals(dummyTVSeries.id, tvSeriesEntity?.id)
        assertEquals(dummyTVSeries.name, tvSeriesEntity?.name)
        assertEquals(dummyTVSeries.firstAirDate, tvSeriesEntity?.firstAirDate)
        assertEquals(dummyTVSeries.overview, tvSeriesEntity?.overview)
        assertEquals(dummyTVSeries.genres, tvSeriesEntity?.genres)
        assertEquals(dummyTVSeries.status, tvSeriesEntity?.status)
        assertEquals(dummyTVSeries.posterPath, tvSeriesEntity?.posterPath)
        assertEquals(dummyTVSeries.voteAverage, tvSeriesEntity?.voteAverage)

        viewModel.getDetailTVSeries().observeForever(tvSeriesObserver)
        verify(tvSeriesObserver).onChanged(dummyTVSeries)
    }
}
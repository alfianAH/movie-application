package com.dicoding.picodiploma.movieapplication.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.movieapplication.BuildConfig
import com.dicoding.picodiploma.movieapplication.data.source.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.MovieResultsItem
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.TVSeriesResultsItem
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
class HomeViewModelTest {
    private val apiKey = BuildConfig.TMBDApiKey

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAppRepository: MovieAppRepository

    @Mock
    private lateinit var movieObserver: Observer<List<MovieResultsItem>>

    @Mock
    private lateinit var tvSeriesObserver: Observer<List<TVSeriesResultsItem>>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieAppRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<MovieResultsItem>>()
        movies.value = dummyMovies

        `when`(movieAppRepository.getMovies(apiKey)).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value

        verify(movieAppRepository).getMovies(apiKey)
        assertNotNull(movieEntities)
        assertEquals(2, movieEntities?.size)

        viewModel.getMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovies)
    }

    @Test
    fun getTVSeries() {
        val dummyTVSeries = DataDummy.generateDummyTVSeries()
        val tvSeries = MutableLiveData<List<TVSeriesResultsItem>>()
        tvSeries.value = dummyTVSeries

        `when`(movieAppRepository.getTVSeries(apiKey)).thenReturn(tvSeries)
        val tvSeriesEntities = viewModel.getTVSeries().value
        verify(movieAppRepository).getTVSeries(apiKey)
        assertNotNull(tvSeriesEntities)
        assertEquals(2, tvSeriesEntities?.size)

        viewModel.getTVSeries().observeForever(tvSeriesObserver)
        verify(tvSeriesObserver).onChanged(dummyTVSeries)
    }
}
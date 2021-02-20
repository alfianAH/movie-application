package com.dicoding.picodiploma.movieapplication.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.picodiploma.movieapplication.data.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.utils.SortUtils
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteListViewModelTest {

    private lateinit var viewModel: FavoriteListViewModel

    @Mock
    private lateinit var movieAppRepository: MovieAppRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var tvSeriesObserver: Observer<PagedList<TVSeriesEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvSeriesPagedList: PagedList<TVSeriesEntity>

    @Before
    fun setUp(){
        viewModel = FavoriteListViewModel(movieAppRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = moviePagedList
        `when`(dummyMovies.size).thenReturn(5)

        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        `when`(movieAppRepository.getFavoriteMovies(SortUtils.NAME)).thenReturn(movies)
        val movieEntities = viewModel.getMovies(SortUtils.NAME).value

        verify(movieAppRepository).getFavoriteMovies(SortUtils.NAME)
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)

        viewModel.getMovies(SortUtils.NAME).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovies)
    }

    @Test
    fun getTVSeries() {
        val dummyTVSeries = tvSeriesPagedList
        `when`(dummyTVSeries.size).thenReturn(5)

        val tvSeries = MutableLiveData<PagedList<TVSeriesEntity>>()
        tvSeries.value = dummyTVSeries

        `when`(movieAppRepository.getFavoriteTVSeries(SortUtils.NAME)).thenReturn(tvSeries)
        val tvSeriesEntities = viewModel.getTVSeries(SortUtils.NAME).value

        verify(movieAppRepository).getFavoriteTVSeries(SortUtils.NAME)
        assertNotNull(tvSeriesEntities)
        assertEquals(5, tvSeriesEntities?.size)

        viewModel.getTVSeries(SortUtils.NAME).observeForever(tvSeriesObserver)
        verify(tvSeriesObserver).onChanged(dummyTVSeries)
    }
}
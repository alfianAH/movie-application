package com.dicoding.picodiploma.movieapplication.ui.tvseries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.picodiploma.movieapplication.data.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.utils.SortUtils
import com.dicoding.picodiploma.movieapplication.valueobject.Resource
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
class TVSeriesViewModelTest {

    private lateinit var viewModel: TVSeriesViewModel

    @Mock
    private lateinit var movieAppRepository: MovieAppRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TVSeriesEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TVSeriesEntity>

    @Before
    fun setUp() {
        viewModel = TVSeriesViewModel(movieAppRepository)
    }

    @Test
    fun getTVSeries() {
        val dummyTVSeries = Resource.success(pagedList)
        `when`(dummyTVSeries.data?.size).thenReturn(15)

        val tvSeries = MutableLiveData<Resource<PagedList<TVSeriesEntity>>>()
        tvSeries.value = dummyTVSeries

        `when`(movieAppRepository.getTVSeries(SortUtils.NAME)).thenReturn(tvSeries)

        val tvSeriesEntities = viewModel.getTVSeries(SortUtils.NAME).value?.data

        verify(movieAppRepository).getTVSeries(SortUtils.NAME)
        assertNotNull(tvSeriesEntities)
        assertEquals(15, tvSeriesEntities?.size)

        viewModel.getTVSeries(SortUtils.NAME).observeForever(observer)
        verify(observer).onChanged(dummyTVSeries)
    }
}
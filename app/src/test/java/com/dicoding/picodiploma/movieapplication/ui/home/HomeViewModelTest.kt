package com.dicoding.picodiploma.movieapplication.ui.home

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel()
    }

    @Test
    fun getMovies() {
        val movieEntities = viewModel.getMovies()

        assertNotNull(movieEntities)
        assertEquals(10, movieEntities.size)
    }

    @Test
    fun getTVSeries() {
        val tvSeriesEntities = viewModel.getTVSeries()

        assertNotNull(tvSeriesEntities)
        assertEquals(10, tvSeriesEntities.size)
    }
}
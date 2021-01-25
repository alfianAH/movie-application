package com.dicoding.picodiploma.movieapplication.ui.detail

import com.dicoding.picodiploma.movieapplication.utils.DataDummy
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTVSeries = DataDummy.generateDummyTVSeries()[0]
    private val movieId = dummyMovie.movieId
    private val tvSeriesId = dummyTVSeries.tvSeriesId

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        // Set ids
        viewModel.setMovieId(movieId)
        viewModel.setTVSeriesId(tvSeriesId)
    }

    @Test
    fun getMovie() {
        val movieEntity = viewModel.getMovie()

        // Check movie entity
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.releaseYear, movieEntity.releaseYear)
        assertEquals(dummyMovie.summary, movieEntity.summary)
        assertEquals(dummyMovie.genre, movieEntity.genre)
        assertEquals(dummyMovie.director, movieEntity.director)
        assertEquals(dummyMovie.imagePath, movieEntity.imagePath)
    }

    @Test
    fun getTVSeries() {
        val tvSeriesEntity = viewModel.getTVSeries()

        // Check movie entity
        assertNotNull(tvSeriesEntity)
        assertEquals(dummyTVSeries.tvSeriesId, tvSeriesEntity.tvSeriesId)
        assertEquals(dummyTVSeries.title, tvSeriesEntity.title)
        assertEquals(dummyTVSeries.releaseYear, tvSeriesEntity.releaseYear)
        assertEquals(dummyTVSeries.summary, tvSeriesEntity.summary)
        assertEquals(dummyTVSeries.genre, tvSeriesEntity.genre)
        assertEquals(dummyTVSeries.creator, tvSeriesEntity.creator)
        assertEquals(dummyTVSeries.imagePath, tvSeriesEntity.imagePath)
    }
}
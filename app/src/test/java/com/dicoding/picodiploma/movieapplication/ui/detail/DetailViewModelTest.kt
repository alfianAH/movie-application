package com.dicoding.picodiploma.movieapplication.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.movieapplication.data.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesGenreEntity
import com.dicoding.picodiploma.movieapplication.utils.DataDummy
import com.dicoding.picodiploma.movieapplication.utils.LiveDataTestUtil
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
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTVSeries = DataDummy.generateDummyTVSeries()[0]
    private val movieId = dummyMovie.id
    private val tvSeriesId = dummyTVSeries.id

    private val dummyMovieGenres = DataDummy.generateDummyMovieGenres(movieId)
    private val dummyTVSeriesGenres = DataDummy.generateDummyTVSeriesGenres(tvSeriesId)

    @Mock
    private lateinit var movieAppRepository: MovieAppRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieDetails>>

    @Mock
    private lateinit var tvSeriesObserver: Observer<Resource<TVSeriesDetails>>

    @Before
    fun setUp(){
        viewModel = DetailViewModel(movieAppRepository)
        viewModel.setMovieId(movieId)
        viewModel.setTVSeriesId(tvSeriesId)
    }

    @Test
    fun getMovieDetail() {
        val dummyMovieDetails = Resource.success(DataDummy.generateDummyDetailMovie(dummyMovie, true))

        val movieDetails = MutableLiveData<Resource<MovieDetails>>()
        movieDetails.value = dummyMovieDetails

        `when`(movieAppRepository.getDetailMovie(movieId)).thenReturn(movieDetails)
        val movieDetailsEntity = LiveDataTestUtil.getValue(viewModel.movieDetail).data

        verify(movieAppRepository).getDetailMovie(movieId)
        assertNotNull(movieDetailsEntity)
        assertEquals(dummyMovie.id, movieDetailsEntity?.movieEntity?.id)
        assertEquals(dummyMovie.title, movieDetailsEntity?.movieEntity?.title)
        assertEquals(dummyMovie.overview, movieDetailsEntity?.movieEntity?.overview)
        assertEquals(dummyMovie.posterPath, movieDetailsEntity?.movieEntity?.posterPath)
        assertEquals(dummyMovie.releaseDate, movieDetailsEntity?.movieEntity?.releaseDate)
        assertEquals(dummyMovie.voteAverage, movieDetailsEntity?.movieEntity?.voteAverage)
        assertEquals(dummyMovie.isFavorite, movieDetailsEntity?.movieEntity?.isFavorite)

        viewModel.movieDetail.observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovieDetails)
    }

    @Test
    fun getTVSeriesDetail() {
        val dummyTVSeriesDetails = Resource.success(DataDummy.generateDummyDetailTVSeries(dummyTVSeries, true))
        val tvSeriesDetails = MutableLiveData<Resource<TVSeriesDetails>>()
        tvSeriesDetails.value = dummyTVSeriesDetails

        `when`(movieAppRepository.getDetailTVSeries(tvSeriesId)).thenReturn(tvSeriesDetails)
        val tvSeriesDetailsEntity = LiveDataTestUtil.getValue(viewModel.tvSeriesDetail).data

        verify(movieAppRepository).getDetailTVSeries(tvSeriesId)
        assertNotNull(tvSeriesDetailsEntity)
        assertEquals(dummyTVSeries.id, tvSeriesDetailsEntity?.tvSeriesEntity?.id)
        assertEquals(dummyTVSeries.title, tvSeriesDetailsEntity?.tvSeriesEntity?.title)
        assertEquals(dummyTVSeries.overview, tvSeriesDetailsEntity?.tvSeriesEntity?.overview)
        assertEquals(dummyTVSeries.posterPath, tvSeriesDetailsEntity?.tvSeriesEntity?.posterPath)
        assertEquals(dummyTVSeries.releaseDate, tvSeriesDetailsEntity?.tvSeriesEntity?.releaseDate)
        assertEquals(dummyTVSeries.voteAverage, tvSeriesDetailsEntity?.tvSeriesEntity?.voteAverage)
        assertEquals(dummyTVSeries.isFavorite, tvSeriesDetailsEntity?.tvSeriesEntity?.isFavorite)

        viewModel.tvSeriesDetail.observeForever(tvSeriesObserver)
        verify(tvSeriesObserver).onChanged(dummyTVSeriesDetails)
    }

    @Test
    fun getMovieGenres(){
        val dummyMovieGenres = Resource.success(DataDummy.generateDummyMovieGenres(movieId))

        val movieGenre = MutableLiveData<Resource<List<MovieGenreEntity>>>()
        movieGenre.value = dummyMovieGenres

        `when`(movieAppRepository.getMovieGenres(movieId)).thenReturn(movieGenre)
        val movieGenresEntity = LiveDataTestUtil.getValue(viewModel.movieGenres).data

        verify(movieAppRepository).getMovieGenres(movieId)
        assertNotNull(movieGenresEntity)
        assertEquals(this.dummyMovieGenres.size, movieGenresEntity?.size)
    }

    @Test
    fun getTVSeriesGenres(){
        val dummyTVSeriesGenres = Resource.success(DataDummy.generateDummyTVSeriesGenres(tvSeriesId))

        val tvSeriesGenre = MutableLiveData<Resource<List<TVSeriesGenreEntity>>>()
        tvSeriesGenre.value = dummyTVSeriesGenres

        `when`(movieAppRepository.getTVSeriesGenres(tvSeriesId)).thenReturn(tvSeriesGenre)
        val tvSeriesGenresEntity = LiveDataTestUtil.getValue(viewModel.tvSeriesGenres).data

        verify(movieAppRepository).getTVSeriesGenres(tvSeriesId)
        assertNotNull(tvSeriesGenresEntity)
        assertEquals(this.dummyTVSeriesGenres.size, tvSeriesGenresEntity?.size)
    }
}
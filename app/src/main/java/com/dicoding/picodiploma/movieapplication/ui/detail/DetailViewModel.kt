package com.dicoding.picodiploma.movieapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.movieapplication.BuildConfig
import com.dicoding.picodiploma.movieapplication.data.DetailMovieResponse
import com.dicoding.picodiploma.movieapplication.data.DetailTVSeriesResponse
import com.dicoding.picodiploma.movieapplication.data.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.utils.DataDummy

class DetailViewModel: ViewModel() {
    private var movieIdTest: Int = 0
    private var tvSeriesIdTest: Int = 0

    private val _movieId = MutableLiveData<DetailMovieResponse>()
    val movieId: LiveData<DetailMovieResponse> = _movieId

    private val _tvSeriesId = MutableLiveData<DetailTVSeriesResponse>()
    val tvSeriesId: LiveData<DetailTVSeriesResponse> = _tvSeriesId

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "DetailViewModel"
        private const val API_KEY = BuildConfig.TMBDApiKey
    }

    fun setSelectedMovie(movieId: Int){
        this.movieIdTest = movieId
    }

    fun setSelectedTVSeries(tvSeriesId: Int){
        this.tvSeriesIdTest = tvSeriesId
    }

    fun getMovie(): MovieEntity{
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.generateDummyMovies()

        for(movieEntity in movieEntities){
            if(movieEntity.movieId == movieIdTest){
                movie = movieEntity
            }
        }

        return movie
    }

    fun getTVSeries(): TVSeriesEntity{
        lateinit var tvSeries: TVSeriesEntity
        val tvSeriesEntities = DataDummy.generateDummyTVSeries()

        for(tvSeriesEntity in tvSeriesEntities){
            if(tvSeriesEntity.tvSeriesId == tvSeriesIdTest){
                tvSeries = tvSeriesEntity
            }
        }

        return tvSeries
    }
}
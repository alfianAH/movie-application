package com.dicoding.picodiploma.movieapplication.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.movieapplication.data.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.utils.DataDummy

class DetailViewModel: ViewModel() {
    private var movieId: Int = 0
    private var tvSeriesId: Int = 0

    fun setSelectedMovie(movieId: Int){
        this.movieId = movieId
    }

    fun setSelectedTVSeries(tvSeriesId: Int){
        this.tvSeriesId = tvSeriesId
    }

    fun getMovie(): MovieEntity{
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.generateDummyMovies()

        for(movieEntity in movieEntities){
            if(movieEntity.movieId == movieId){
                movie = movieEntity
            }
        }

        return movie
    }

    fun getTVSeries(): TVSeriesEntity{
        lateinit var tvSeries: TVSeriesEntity
        val tvSeriesEntities = DataDummy.generateDummyTVSeries()

        for(tvSeriesEntity in tvSeriesEntities){
            if(tvSeriesEntity.tvSeriesId == tvSeriesId){
                tvSeries = tvSeriesEntity
            }
        }

        return tvSeries
    }
}
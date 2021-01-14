package com.dicoding.picodiploma.movieapplication.ui.home

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.movieapplication.data.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.utils.DataDummy

class HomeViewModel: ViewModel() {
    fun getMovies(): List<MovieEntity> = DataDummy.generateDummyMovies()

    fun getTVSeries(): List<TVSeriesEntity> = DataDummy.generateDummyTVSeries()
}
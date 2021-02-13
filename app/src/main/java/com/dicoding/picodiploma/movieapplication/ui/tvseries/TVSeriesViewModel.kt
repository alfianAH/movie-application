package com.dicoding.picodiploma.movieapplication.ui.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.picodiploma.movieapplication.data.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.valueobject.Resource

class TVSeriesViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    fun getTVSeries(): LiveData<Resource<PagedList<TVSeriesEntity>>> =
            movieAppRepository.getTVSeries()
}
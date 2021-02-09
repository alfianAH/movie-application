package com.dicoding.picodiploma.movieapplication.data

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.DetailMovieResponse
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.DetailTVSeriesResponse
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.MovieResultsItem
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.TVSeriesResultsItem

interface MovieAppDataSource {
    fun getMovies(apiKey: String)                           : LiveData<List<MovieResultsItem>>
    fun getTVSeries(apiKey: String)                         : LiveData<List<TVSeriesResultsItem>>
    fun getDetailMovie(apiKey: String, movieId: Int)        : LiveData<DetailMovieResponse>
    fun getDetailTVSeries(apiKey: String, tvSeriesId: Int)  : LiveData<DetailTVSeriesResponse>
}
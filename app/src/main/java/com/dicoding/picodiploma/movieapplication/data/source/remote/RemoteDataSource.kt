package com.dicoding.picodiploma.movieapplication.data.source.remote

import com.dicoding.picodiploma.movieapplication.data.source.remote.response.DetailMovieResponse
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.DetailTVSeriesResponse
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.MovieResultsItem
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.TVSeriesResultsItem
import com.dicoding.picodiploma.movieapplication.utils.APILoaderHelper

class RemoteDataSource private constructor(private val apiLoaderHelper: APILoaderHelper){

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiLoaderHelper: APILoaderHelper): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(apiLoaderHelper)
            }
    }

    fun getMovies(apiKey: String): List<MovieResultsItem> = apiLoaderHelper.findMovies(apiKey)

    fun getTVSeries(apiKey: String): List<TVSeriesResultsItem> = apiLoaderHelper.findTVSeries(apiKey)

    fun getDetailMovie(apiKey: String, movieId: Int): DetailMovieResponse =
        apiLoaderHelper.findDetailMovie(apiKey, movieId)

    fun getDetailTVSeries(apiKey: String, tvSeriesId: Int): DetailTVSeriesResponse =
        apiLoaderHelper.findDetailTVSeries(apiKey, tvSeriesId)
}
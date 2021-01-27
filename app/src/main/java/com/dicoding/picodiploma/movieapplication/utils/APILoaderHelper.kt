package com.dicoding.picodiploma.movieapplication.utils

import com.dicoding.picodiploma.movieapplication.api.ApiConfig
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.*
import retrofit2.Call

class APILoaderHelper {

    fun findMovies(apiKey: String): Call<MovieResponse>{
        return ApiConfig.getApiService().getMovie(apiKey)
    }

    fun findTVSeries(apiKey: String): Call<TVSeriesResponse>{
        return ApiConfig.getApiService().getTVSeries(apiKey)
    }

    fun findDetailMovie(apiKey: String, movieId: Int): Call<DetailMovieResponse>{
        return ApiConfig.getApiService().getDetailMovie(movieId,apiKey)
    }

    fun findDetailTVSeries(apiKey: String, tvSeriesId: Int): Call<DetailTVSeriesResponse>{
        return ApiConfig.getApiService().getDetailTVSeries(tvSeriesId, apiKey)
    }
}
package com.dicoding.picodiploma.movieapplication.api

import com.dicoding.picodiploma.movieapplication.data.GenresResponse
import com.dicoding.picodiploma.movieapplication.data.MovieResponse
import com.dicoding.picodiploma.movieapplication.data.TVSeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/upcoming")
    fun getMovie(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("tv/top_rated")
    fun getTVSeries(
        @Query("api_key") apiKey: String
    ): Call<TVSeriesResponse>

    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String
    ): Call<GenresResponse>
}
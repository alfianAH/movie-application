package com.dicoding.picodiploma.movieapplication.api

import com.dicoding.picodiploma.movieapplication.data.DetailMovieResponse
import com.dicoding.picodiploma.movieapplication.data.DetailTVSeriesResponse
import com.dicoding.picodiploma.movieapplication.data.MovieResponse
import com.dicoding.picodiploma.movieapplication.data.TVSeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{movie_id}")
    fun getDetailMovie(
            @Path("movie_id") movieId: Int,
            @Query("api_key") apiKey: String
    ): Call<DetailMovieResponse>

    @GET("tv/{tv_id}")
    fun getDetailTVSeries(
            @Path("tv_id") tvSeriesId: Int,
            @Query("api_key") apiKey: String
    ): Call<DetailTVSeriesResponse>
}
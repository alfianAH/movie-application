package com.dicoding.picodiploma.movieapplication.api

import com.dicoding.picodiploma.movieapplication.data.GenresResponse
import com.dicoding.picodiploma.movieapplication.data.MovieResponse
import com.dicoding.picodiploma.movieapplication.data.TVSeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/movie/upcoming?api_key={apiKey}&language=en-US&page=1")
    fun getMovie(
        @Path("apiKey") apiKey: String
    ): Call<MovieResponse>

    @GET("/tv/top_rated?api_key={apiKey}&language=en-US&page=1")
    fun getTVSeries(
        @Path("apiKey") apiKey: String
    ): Call<TVSeriesResponse>

    @GET("/genre/movie/list?api_key={apiKey}&language=en-US")
    fun getGenres(
        @Path("apiKey") apiKey: String
    ): Call<GenresResponse>

    // https://image.tmdb.org/t/p/w500/wwemzKWzjKYJFfCeiB57q3r4Bcm.png
}
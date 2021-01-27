package com.dicoding.picodiploma.movieapplication.data.source.remote

import com.dicoding.picodiploma.movieapplication.data.source.remote.response.*
import com.dicoding.picodiploma.movieapplication.utils.APILoaderHelper
import retrofit2.Call

class RemoteDataSource private constructor(private val apiLoaderHelper: APILoaderHelper){

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiLoaderHelper: APILoaderHelper): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(apiLoaderHelper)
            }
    }

    fun getMovies(apiKey: String, callback: LoadMoviesCallback){
        callback.onAllMoviesReceived(apiLoaderHelper.findMovies(apiKey))
    }

    fun getTVSeries(apiKey: String, callback: LoadTVSeriesCallback){
        callback.onAllTVSeriesReceived(apiLoaderHelper.findTVSeries(apiKey))
    }

    fun getDetailMovie(apiKey: String, movieId: Int, callback: LoadDetailMovieCallback){
        callback.onDetailMovieReceived(apiLoaderHelper.findDetailMovie(apiKey, movieId))
    }

    fun getDetailTVSeries(apiKey: String, tvSeriesId: Int, callback: LoadDetailTVSeriesCallback){
        callback.onDetailTVSeriesReceived(apiLoaderHelper.findDetailTVSeries(apiKey, tvSeriesId))
    }

    /**
     * Interfaces to call client for each responses
     */

    interface LoadMoviesCallback{
        fun onAllMoviesReceived(client : Call<MovieResponse>)
    }

    interface LoadTVSeriesCallback{
        fun onAllTVSeriesReceived(client: Call<TVSeriesResponse>)
    }

    interface LoadDetailMovieCallback{
        fun onDetailMovieReceived(client: Call<DetailMovieResponse>)
    }

    interface LoadDetailTVSeriesCallback{
        fun onDetailTVSeriesReceived(client: Call<DetailTVSeriesResponse>)
    }
}
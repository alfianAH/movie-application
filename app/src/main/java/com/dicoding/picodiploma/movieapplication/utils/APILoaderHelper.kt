package com.dicoding.picodiploma.movieapplication.utils

import android.util.Log
import com.dicoding.picodiploma.movieapplication.api.ApiConfig
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APILoaderHelper {

    companion object{
        private const val TAG = "APILoaderHelper"
    }

    fun findMovies(apiKey: String): List<MovieResultsItem>{
        val client = ApiConfig.getApiService().getMovie(apiKey)
        val movies = ArrayList<MovieResultsItem>()

        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                if(response.isSuccessful){
                    movies.addAll(response.body()?.results as List<MovieResultsItem>)
                    Log.d("Loader", "Movie size(loop): ${movies.size}")
                } else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        Log.d("Loader", "Movie size: ${movies.size}")
        return movies
    }

    fun findTVSeries(apiKey: String): List<TVSeriesResultsItem>{
        val client = ApiConfig.getApiService().getTVSeries(apiKey)
        val tvSeries = ArrayList<TVSeriesResultsItem>()

        client.enqueue(object : Callback<TVSeriesResponse> {
            override fun onResponse(
                call: Call<TVSeriesResponse>,
                response: Response<TVSeriesResponse>
            ) {
                if(response.isSuccessful){
                    tvSeries.addAll(response.body()?.results as List<TVSeriesResultsItem>)
                } else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TVSeriesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return tvSeries
    }

    fun findDetailMovie(apiKey: String, movieId: Int): DetailMovieResponse{
        val client = ApiConfig.getApiService().getDetailMovie(movieId,apiKey)
        var detailMovieResponse: DetailMovieResponse? = null

        client.enqueue(object: Callback<DetailMovieResponse> {
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                if(response.isSuccessful){
                    detailMovieResponse = response.body()
                } else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return detailMovieResponse as DetailMovieResponse
    }

    fun findDetailTVSeries(apiKey: String, tvSeriesId: Int): DetailTVSeriesResponse{
        val client = ApiConfig.getApiService().getDetailTVSeries(tvSeriesId, apiKey)
        var detailTVSeriesResponse: DetailTVSeriesResponse? = null

        client.enqueue(object: Callback<DetailTVSeriesResponse> {
            override fun onResponse(call: Call<DetailTVSeriesResponse>, response: Response<DetailTVSeriesResponse>) {
                if(response.isSuccessful){
                    detailTVSeriesResponse = response.body()
                } else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailTVSeriesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return detailTVSeriesResponse as DetailTVSeriesResponse
    }
}
package com.dicoding.picodiploma.movieapplication.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.movieapplication.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FakeMovieAppRepository (private val remoteDataSource: RemoteDataSource): MovieAppDataSource{

    companion object{
        private const val TAG = "FakeMovieAppRepository"
    }
    
    override fun getMovies(apiKey: String): LiveData<List<MovieResultsItem>> {
        val movieResults = MutableLiveData<List<MovieResultsItem>>()

        // Get movie
        remoteDataSource.getMovies(apiKey, object: RemoteDataSource.LoadMoviesCallback {

            override fun onAllMoviesReceived(client: Call<MovieResponse>) {
                // Get movie from API
                client.enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(call: Call<MovieResponse>,
                                            response: Response<MovieResponse>) {
                        // If response is successful, post value
                        if(response.isSuccessful){
                            movieResults.postValue(response.body()?.results)
                        } else{
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                    }
                })
            }
        })
        
        return movieResults
    }

    override fun getTVSeries(apiKey: String): LiveData<List<TVSeriesResultsItem>> {
        val tvSeriesResults = MutableLiveData<List<TVSeriesResultsItem>>()

        // Get TV Series
        remoteDataSource.getTVSeries(apiKey, object: RemoteDataSource.LoadTVSeriesCallback {

            override fun onAllTVSeriesReceived(client: Call<TVSeriesResponse>) {
                // Get TV Series from API
                client.enqueue(object : Callback<TVSeriesResponse> {
                    override fun onResponse(
                            call: Call<TVSeriesResponse>,
                            response: Response<TVSeriesResponse>
                    ) {
                        // If response is successful, post value
                        if(response.isSuccessful){
                            tvSeriesResults.postValue(response.body()?.results)
                        } else{
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<TVSeriesResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                    }
                })
            }
        })

        return tvSeriesResults
    }

    override fun getDetailMovie(apiKey: String, movieId: Int): LiveData<DetailMovieResponse> {
        val detailMovieResult = MutableLiveData<DetailMovieResponse>()
        // Get Detail Movie
        remoteDataSource.getDetailMovie(apiKey, movieId,
                object : RemoteDataSource.LoadDetailMovieCallback {

            override fun onDetailMovieReceived(client: Call<DetailMovieResponse>) {
                // Get Detail Movie from API
                client.enqueue(object: Callback<DetailMovieResponse> {
                    override fun onResponse(call: Call<DetailMovieResponse>,
                                            response: Response<DetailMovieResponse>) {
                        // If response is successful, post value
                        if(response.isSuccessful){
                            detailMovieResult.postValue(response.body())
                        } else{
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                    }
                })
            }

        })

        return detailMovieResult
    }

    override fun getDetailTVSeries(apiKey: String, tvSeriesId: Int): LiveData<DetailTVSeriesResponse> {
        val detailTVSeriesResult = MutableLiveData<DetailTVSeriesResponse>()

        // Get Detail TV Series
        remoteDataSource.getDetailTVSeries(apiKey,
                tvSeriesId,
                object: RemoteDataSource.LoadDetailTVSeriesCallback {

            override fun onDetailTVSeriesReceived(client: Call<DetailTVSeriesResponse>) {
                // Get Detail TV Series from API
                client.enqueue(object: Callback<DetailTVSeriesResponse> {
                    override fun onResponse(call: Call<DetailTVSeriesResponse>,
                                            response: Response<DetailTVSeriesResponse>) {
                        // If response is successful, post value
                        if(response.isSuccessful){
                            detailTVSeriesResult.postValue(response.body())
                        } else{
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<DetailTVSeriesResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                    }
                })
            }

        })

        return detailTVSeriesResult
    }
}
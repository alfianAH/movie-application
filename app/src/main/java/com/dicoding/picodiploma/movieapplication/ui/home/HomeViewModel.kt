package com.dicoding.picodiploma.movieapplication.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.movieapplication.BuildConfig
import com.dicoding.picodiploma.movieapplication.api.ApiConfig
import com.dicoding.picodiploma.movieapplication.data.*
import com.dicoding.picodiploma.movieapplication.utils.DataDummy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private val _movie = MutableLiveData<List<MovieResultsItem>>()
    val movie: LiveData<List<MovieResultsItem>> = _movie

    private val _tvSeries = MutableLiveData<List<TVSeriesResultsItem>>()
    val tvSeries: LiveData<List<TVSeriesResultsItem>> = _tvSeries

    private val _genres = MutableLiveData<List<GenresItem>>()
    val genres: LiveData<List<GenresItem>> = _genres

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "HomeViewModel"
        private const val API_KEY = BuildConfig.TMBDApiKey
    }

    fun findMovies(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getMovie(API_KEY)

        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _isLoading.value = false

                if(response.isSuccessful){
                    _movie.value = response.body()?.results

                    // Content value
                    response.body()?.results
                } else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun findTVSeries(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getTVSeries(API_KEY)

        client.enqueue(object : Callback<TVSeriesResponse> {
            override fun onResponse(
                call: Call<TVSeriesResponse>,
                response: Response<TVSeriesResponse>
            ) {
                _isLoading.value = false

                if(response.isSuccessful){
                    _tvSeries.value = response.body()?.results

                    // Content value

                } else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TVSeriesResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun findGenres(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getGenres(API_KEY)

        client.enqueue(object : Callback<GenresResponse> {
            override fun onResponse(
                call: Call<GenresResponse>,
                response: Response<GenresResponse>
            ) {
                _isLoading.value = false

                if(response.isSuccessful){
                    _genres.value = response.body()?.genres

                    // Content value

                } else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GenresResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getMovies(): List<MovieEntity> = DataDummy.generateDummyMovies()

    fun getTVSeries(): List<TVSeriesEntity> = DataDummy.generateDummyTVSeries()
}
package com.dicoding.picodiploma.movieapplication.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.movieapplication.BuildConfig
import com.dicoding.picodiploma.movieapplication.api.ApiConfig
import com.dicoding.picodiploma.movieapplication.data.DetailMovieResponse
import com.dicoding.picodiploma.movieapplication.data.DetailTVSeriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    private var movieId: Int = 0
    private var tvSeriesId: Int = 0

    private val _detailMovie = MutableLiveData<DetailMovieResponse>()
    val detailMovie: LiveData<DetailMovieResponse> = _detailMovie

    private val _detailTvSeries = MutableLiveData<DetailTVSeriesResponse>()
    val detailTvSeries: LiveData<DetailTVSeriesResponse> = _detailTvSeries

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "DetailViewModel"
        private const val API_KEY = BuildConfig.TMBDApiKey
    }

    fun setMovieId(movieId: Int){
        this.movieId = movieId
    }

    fun setTVSeriesId(tvSeriesId: Int){
        this.tvSeriesId = tvSeriesId
    }

    fun findDetailMovie(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailMovie(movieId, API_KEY)

        client.enqueue(object: Callback<DetailMovieResponse> {
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                _isLoading.value = false

                if(response.isSuccessful){
                    _detailMovie.value = response.body()
                } else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun findDetailTVSeries(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailTVSeries(tvSeriesId, API_KEY)

        client.enqueue(object: Callback<DetailTVSeriesResponse> {
            override fun onResponse(call: Call<DetailTVSeriesResponse>, response: Response<DetailTVSeriesResponse>) {
                _isLoading.value = false

                if(response.isSuccessful){
                    _detailTvSeries.value = response.body()
                } else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailTVSeriesResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
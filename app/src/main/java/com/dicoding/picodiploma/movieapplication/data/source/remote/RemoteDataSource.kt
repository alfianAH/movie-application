package com.dicoding.picodiploma.movieapplication.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.movieapplication.BuildConfig
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.*
import com.dicoding.picodiploma.movieapplication.utils.APILoaderHelper
import com.dicoding.picodiploma.movieapplication.utils.EspressoIdlingResources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiLoaderHelper: APILoaderHelper){

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiLoaderHelper: APILoaderHelper): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(apiLoaderHelper)
            }

        private const val TAG = "RemoteDataSource"
        private const val API_KEY = BuildConfig.TMBDApiKey
    }

    fun getMovies(): LiveData<ApiResponse<List<MovieResultsItem>>> {
        EspressoIdlingResources.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieResultsItem>>>()

        apiLoaderHelper.findMovies(API_KEY).enqueue(object: Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(response.isSuccessful){
                    resultMovie.value =
                            ApiResponse.success(response.body()?.results as List<MovieResultsItem>)
                } else{
                    Log.e(TAG, "onResponse: ${response.message()}")
                }

                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        return resultMovie
    }

    fun getTVSeries(): LiveData<ApiResponse<List<TVSeriesResultsItem>>>{
        EspressoIdlingResources.increment()
        val resultTVSeries = MutableLiveData<ApiResponse<List<TVSeriesResultsItem>>>()

        apiLoaderHelper.findTVSeries(API_KEY).enqueue(object: Callback<TVSeriesResponse> {
            override fun onResponse(call: Call<TVSeriesResponse>, response: Response<TVSeriesResponse>) {
                if(response.isSuccessful){
                    resultTVSeries.value =
                            ApiResponse.success(response.body()?.results as List<TVSeriesResultsItem>)
                } else{
                    Log.e(TAG, "onResponse: ${response.message()}")
                }

                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<TVSeriesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        return resultTVSeries
    }

    fun getMovieGenres(movieId: Int): LiveData<ApiResponse<List<GenresItem>>>{
        EspressoIdlingResources.increment()
        val resultMovieGenres = MutableLiveData<ApiResponse<List<GenresItem>>>()

        apiLoaderHelper.findDetailMovie(API_KEY, movieId).enqueue(object: Callback<DetailMovieResponse>{
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                if(response.isSuccessful){
                    resultMovieGenres.value =
                            ApiResponse.success(response.body()?.genres as List<GenresItem>)
                } else{
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        return resultMovieGenres
    }

    fun getTVSeriesGenres(tvSeriesId: Int): LiveData<ApiResponse<List<GenresItem>>>{
        EspressoIdlingResources.increment()
        val resultTVSeriesGenres = MutableLiveData<ApiResponse<List<GenresItem>>>()

        apiLoaderHelper.findDetailTVSeries(API_KEY, tvSeriesId).enqueue(object: Callback<DetailTVSeriesResponse>{
            override fun onResponse(call: Call<DetailTVSeriesResponse>, response: Response<DetailTVSeriesResponse>) {
                if(response.isSuccessful){
                    resultTVSeriesGenres.value =
                            ApiResponse.success(response.body()?.genres as List<GenresItem>)
                } else{
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailTVSeriesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        return resultTVSeriesGenres
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        EspressoIdlingResources.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<DetailMovieResponse>>()

        apiLoaderHelper.findDetailMovie(API_KEY, movieId).enqueue(object: Callback<DetailMovieResponse>{
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                if(response.isSuccessful){
                    resultDetailMovie.value =
                            ApiResponse.success(response.body() as DetailMovieResponse)
                } else{
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        return resultDetailMovie
    }

    fun getDetailTVSeries(tvSeriesId: Int): LiveData<ApiResponse<DetailTVSeriesResponse>>{
        EspressoIdlingResources.increment()
        val resultDetailTVSeries = MutableLiveData<ApiResponse<DetailTVSeriesResponse>>()

        apiLoaderHelper.findDetailTVSeries(API_KEY, tvSeriesId).enqueue(object: Callback<DetailTVSeriesResponse>{
            override fun onResponse(call: Call<DetailTVSeriesResponse>, response: Response<DetailTVSeriesResponse>) {
                if(response.isSuccessful){
                    resultDetailTVSeries.value =
                            ApiResponse.success(response.body() as DetailTVSeriesResponse)
                } else{
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailTVSeriesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        return resultDetailTVSeries
    }
}
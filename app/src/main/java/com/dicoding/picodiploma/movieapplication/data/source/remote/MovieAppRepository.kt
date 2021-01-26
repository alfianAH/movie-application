package com.dicoding.picodiploma.movieapplication.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.movieapplication.data.source.MovieAppDataSource
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.DetailMovieResponse
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.DetailTVSeriesResponse
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.MovieResultsItem
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.TVSeriesResultsItem

class MovieAppRepository private constructor(private val remoteDataSource: RemoteDataSource): MovieAppDataSource{
    
    companion object{
        @Volatile
        private var instance: MovieAppRepository? = null
        
        fun getInstance(remoteDataSource: RemoteDataSource): MovieAppRepository = 
            instance ?: synchronized(this){
                instance ?: MovieAppRepository(remoteDataSource)
            }
    }
    
    override fun getMovies(apiKey: String): LiveData<List<MovieResultsItem>> {
        val movieResponses = remoteDataSource.getMovies(apiKey)
        val movieList = ArrayList<MovieResultsItem>()
        val movieResults = MutableLiveData<List<MovieResultsItem>>()

        movieList.addAll(movieResponses)
        movieResults.postValue(movieList)
        
        return movieResults
    }

    override fun getTVSeries(apiKey: String): LiveData<List<TVSeriesResultsItem>> {
        val tvSeriesResponses = remoteDataSource.getTVSeries(apiKey)
        val tvSeriesList = ArrayList<TVSeriesResultsItem>()
        val tvSeriesResults = MutableLiveData<List<TVSeriesResultsItem>>()

        tvSeriesList.addAll(tvSeriesResponses)
        tvSeriesResults.postValue(tvSeriesList)

        return tvSeriesResults
    }

    override fun getDetailMovie(apiKey: String, movieId: Int): LiveData<DetailMovieResponse> {
        val detailMovieResult = MutableLiveData<DetailMovieResponse>()
        detailMovieResult.postValue(remoteDataSource.getDetailMovie(apiKey, movieId))

        return detailMovieResult
    }

    override fun getDetailTVSeries(apiKey: String, tvSeriesId: Int): LiveData<DetailTVSeriesResponse> {
        val detailTVSeriesResult = MutableLiveData<DetailTVSeriesResponse>()
        detailTVSeriesResult.postValue(remoteDataSource.getDetailTVSeries(apiKey, tvSeriesId))

        return detailTVSeriesResult
    }
}
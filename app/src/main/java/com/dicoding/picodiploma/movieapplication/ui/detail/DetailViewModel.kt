package com.dicoding.picodiploma.movieapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.movieapplication.data.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesGenreEntity
import com.dicoding.picodiploma.movieapplication.valueobject.Resource

class DetailViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {
    private val movieId = MutableLiveData<Int>()
    private val tvSeriesId = MutableLiveData<Int>()

    fun setMovieId(movieId: Int){
        this.movieId.value = movieId
    }

    fun setTVSeriesId(tvSeriesId: Int){
        this.tvSeriesId.value = tvSeriesId
    }

    var movieDetail: LiveData<Resource<MovieDetails>> =
            Transformations.switchMap(movieId) { movieId ->
                movieAppRepository.getDetailMovie(movieId)
            }

    var tvSeriesDetail: LiveData<Resource<TVSeriesDetails>> =
            Transformations.switchMap(tvSeriesId) { tvSeriesId ->
                movieAppRepository.getDetailTVSeries(tvSeriesId)
            }

    var movieGenres: LiveData<Resource<List<MovieGenreEntity>>> =
            Transformations.switchMap(movieId) { movieId ->
                movieAppRepository.getMovieGenres(movieId)
            }

    var tvSeriesGenres: LiveData<Resource<List<TVSeriesGenreEntity>>> =
            Transformations.switchMap(tvSeriesId) { tvSeriesId ->
                movieAppRepository.getTVSeriesGenres(tvSeriesId)
            }

    /**
     * Set favorite movie
     */
    @JvmName("setFavorite1")
    fun setFavorite(movieResource: Resource<MovieDetails>?){
//        val movieResource = movieDetail.value

        if(movieResource != null){
            val movie = movieResource.data

            if(movie != null){
                val movieEntity = movie.movieEntity
                val newState = !movieEntity.isFavorite

                movieAppRepository.setFavoriteMovie(movieEntity, newState)
            }
        }
    }

    /**
     * Set favorite tv series
     */
    fun setFavorite(tvSeriesResource: Resource<TVSeriesDetails>?){
//        val tvSeriesResource = tvSeriesDetail.value

        if(tvSeriesResource != null){
            val tvSeries = tvSeriesResource.data

            if(tvSeries != null){
                val tvSeriesEntity = tvSeries.tvSeriesEntity
                val newState = !tvSeriesEntity.isFavorite

                movieAppRepository.setFavoriteTVSeries(tvSeriesEntity, newState)
            }
        }
    }
}
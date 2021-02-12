package com.dicoding.picodiploma.movieapplication.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.movieapplication.data.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.ui.detail.DetailViewModel
import com.dicoding.picodiploma.movieapplication.ui.favorite.FavoriteListViewModel
import com.dicoding.picodiploma.movieapplication.di.Injection
import com.dicoding.picodiploma.movieapplication.ui.movie.MovieViewModel
import com.dicoding.picodiploma.movieapplication.ui.tvseries.TVSeriesViewModel

class ViewModelFactory private constructor(private val movieAppRepository: MovieAppRepository):
    ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(FavoriteListViewModel::class.java) -> {
                FavoriteListViewModel(movieAppRepository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieAppRepository) as T
            }

            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieAppRepository) as T
            }

            modelClass.isAssignableFrom(TVSeriesViewModel::class.java) -> {
                TVSeriesViewModel(movieAppRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
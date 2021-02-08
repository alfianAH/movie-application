package com.dicoding.picodiploma.movieapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.movieapplication.data.source.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.ui.detail.DetailViewModel
import com.dicoding.picodiploma.movieapplication.ui.favorite.FavoriteViewModel
import com.dicoding.picodiploma.movieapplication.di.Injection

class ViewModelFactory private constructor(private val movieAppRepository: MovieAppRepository):
    ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(movieAppRepository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieAppRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
package com.dicoding.picodiploma.movieapplication.utils

import com.dicoding.picodiploma.movieapplication.data.source.remote.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): MovieAppRepository{
        val remoteDataSource = RemoteDataSource.getInstance(APILoaderHelper())

        return MovieAppRepository.getInstance(remoteDataSource)
    }
}
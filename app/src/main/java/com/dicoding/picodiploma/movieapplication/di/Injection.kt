package com.dicoding.picodiploma.movieapplication.di

import com.dicoding.picodiploma.movieapplication.data.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.movieapplication.utils.APILoaderHelper

object Injection {
    fun provideRepository(): MovieAppRepository {
        val remoteDataSource = RemoteDataSource.getInstance(APILoaderHelper())

        return MovieAppRepository.getInstance(remoteDataSource)
    }
}
package com.dicoding.picodiploma.movieapplication.di

import android.content.Context
import com.dicoding.picodiploma.movieapplication.data.MovieAppRepository
import com.dicoding.picodiploma.movieapplication.data.source.local.LocalDataSource
import com.dicoding.picodiploma.movieapplication.data.source.local.room.MovieAppDatabase
import com.dicoding.picodiploma.movieapplication.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.movieapplication.utils.APILoaderHelper
import com.dicoding.picodiploma.movieapplication.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieAppRepository {
        val database = MovieAppDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(APILoaderHelper())
        val localDataSource = LocalDataSource.getInstance(database.movieAppDao())
        val appExecutors = AppExecutors()

        return MovieAppRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
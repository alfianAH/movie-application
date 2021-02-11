package com.dicoding.picodiploma.movieapplication.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetailEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity

@Database(entities = [MovieEntity::class,
    MovieDetailEntity::class,
    TVSeriesEntity::class,
    TVSeriesDetailEntity::class
],
    version = 1,
    exportSchema = false
)
abstract class MovieAppDatabase: RoomDatabase() {
    abstract fun movieAppDao(): MovieAppDao

    companion object{
        @Volatile
        private var INSTANCE: MovieAppDatabase? = null
        private const val DATABASE_NAME = "MovieApp.db"

        fun getInstance(context: Context): MovieAppDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    MovieAppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
    }
}
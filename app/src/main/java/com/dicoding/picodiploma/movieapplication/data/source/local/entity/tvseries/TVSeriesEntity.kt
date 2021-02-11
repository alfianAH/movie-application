package com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvSeriesEntities")
data class TVSeriesEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvSeriesId")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "posterPath")
    val posterPath: String,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double
)
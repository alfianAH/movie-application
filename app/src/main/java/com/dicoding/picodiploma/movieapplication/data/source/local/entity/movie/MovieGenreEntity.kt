package com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "movieGenreEntities",
    primaryKeys = ["genreId", "movieId"],
    foreignKeys = [ForeignKey(entity = MovieEntity::class,
        parentColumns = ["movieId"],
        childColumns = ["movieId"]
    )],
    indices = [Index(value = ["genreId"]),
        Index(value = ["movieId"])
    ]
)
data class MovieGenreEntity(
    @NonNull
    @ColumnInfo(name = "movieId")
    val movieId: Int,

    @NonNull
    @ColumnInfo(name = "genreId")
    val genreId: Int,

    @NonNull
    @ColumnInfo(name = "genreName")
    val genreName: String
)
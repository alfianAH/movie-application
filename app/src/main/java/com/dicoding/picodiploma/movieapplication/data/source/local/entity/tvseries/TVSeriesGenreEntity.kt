package com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "tvSeriesGenreEntities",
    primaryKeys = ["genreId", "tvSeriesId"],
    foreignKeys = [ForeignKey(entity = TVSeriesEntity::class,
        parentColumns = ["tvSeriesId"],
        childColumns = ["tvSeriesId"]
    )],
    indices = [Index(value = ["genreId"]),
        Index(value = ["tvSeriesId"])
    ]
)
data class TVSeriesGenreEntity(
    @NonNull
    @ColumnInfo(name = "tvSeriesId")
    val tvSeriesId: Int,

    @NonNull
    @ColumnInfo(name = "genreId")
    val genreId: Int,

    @NonNull
    @ColumnInfo(name = "genreName")
    val genreName: String
)
package com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries

import androidx.room.Embedded
import androidx.room.Relation

data class TVSeriesDetails (
    @Embedded
    val tvSeriesEntity: TVSeriesEntity,

    @Relation(parentColumn = "tvSeriesId", entityColumn = "tvSeriesId")
    val genres: List<TVSeriesGenreEntity>
)
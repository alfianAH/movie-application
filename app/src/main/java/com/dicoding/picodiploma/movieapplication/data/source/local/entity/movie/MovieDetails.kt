package com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie

import androidx.room.Embedded
import androidx.room.Relation

data class MovieDetails (
    @Embedded
    val movieEntity: MovieEntity,

    @Relation(parentColumn = "movieId", entityColumn = "movieId")
    val genres: List<MovieGenreEntity>
)
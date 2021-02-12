package com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie

import androidx.room.Embedded
import androidx.room.Relation
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.GenresItem

data class MovieWithGenres (
    @Embedded
    val movieEntity: MovieEntity,

    @Relation(parentColumn = "movieId", entityColumn = "movieId")
    val genres: List<GenresItem>
)
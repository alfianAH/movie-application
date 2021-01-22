package com.dicoding.picodiploma.movieapplication.data

data class TVSeriesEntity(
        val tvSeriesId: Int,
        val title: String,
        val releaseYear: Int,
        val summary: String,
        val genre: String,
        val creator: String,
        val imagePath: String
)
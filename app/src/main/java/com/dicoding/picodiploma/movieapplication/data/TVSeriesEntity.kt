package com.dicoding.picodiploma.movieapplication.data

data class TVSeriesEntity(
        var tvSeriesId: Int,
        var title: String,
        var releaseYear: Int,
        var summary: String,
        var genre: String,
        var creator: String,
        var imagePath: String
)
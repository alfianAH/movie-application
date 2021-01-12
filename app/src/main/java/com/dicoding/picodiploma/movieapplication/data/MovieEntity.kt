package com.dicoding.picodiploma.movieapplication.data

data class MovieEntity (
        var movieId: Int,
        var title: String,
        var releaseYear: Int,
        var summary: String,
        var genre: String,
        var director: String,
        var imagePath: String
)
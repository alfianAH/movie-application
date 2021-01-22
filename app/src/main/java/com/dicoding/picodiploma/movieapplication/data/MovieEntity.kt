package com.dicoding.picodiploma.movieapplication.data

data class MovieEntity (
        val movieId: Int,
        val title: String,
        val releaseYear: Int,
        val summary: String,
        val genre: String,
        val director: String,
        val imagePath: String
)
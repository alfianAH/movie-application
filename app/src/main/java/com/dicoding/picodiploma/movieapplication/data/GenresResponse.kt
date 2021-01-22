package com.dicoding.picodiploma.movieapplication.data

data class GenresResponse(
	val genres: List<GenresItem>
)

data class GenresItem(
	val name: String,
	val id: Int
)


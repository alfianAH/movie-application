package com.dicoding.picodiploma.movieapplication.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TVSeriesResponse(
	@field:SerializedName("results")
	val results: List<TVSeriesResultsItem>
)

data class TVSeriesResultsItem(

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,
)

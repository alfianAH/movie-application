package com.dicoding.picodiploma.movieapplication.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenresItem(

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("id")
        val id: Int
)

data class ProductionCountriesItem(

        @field:SerializedName("iso_3166_1")
        val iso31661: String,

        @field:SerializedName("name")
        val name: String
)

data class ProductionCompaniesItem(

        @field:SerializedName("logo_path")
        val logoPath: String,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("origin_country")
        val originCountry: String
)

data class SpokenLanguagesItem(

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("iso_639_1")
        val iso6391: String,

        @field:SerializedName("english_name")
        val englishName: String
)
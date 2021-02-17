package com.dicoding.picodiploma.movieapplication.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object SortUtils {
    const val NAME = "name"
    const val RATING = "rating"

    /**
     * Get sorted tv series by name and rating
     */
    fun getSortedMovie(filter: String): SimpleSQLiteQuery{
        val simpleQuery = StringBuilder().append("SELECT * FROM movieEntities ")

        when(filter){
            NAME -> simpleQuery.append("ORDER BY title ASC")
            RATING -> simpleQuery.append("ORDER BY voteAverage DESC")
        }

        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    /**
     * Get sorted tv series by name and rating
     */
    fun getSortedTVSeries(filter: String): SimpleSQLiteQuery{
        val simpleQuery = StringBuilder().append("SELECT * FROM tvSeriesEntities ")

        when(filter){
            NAME -> simpleQuery.append("ORDER BY title ASC")
            RATING -> simpleQuery.append("ORDER BY voteAverage DESC")
        }

        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}
package com.dicoding.picodiploma.movieapplication.utils

import java.text.SimpleDateFormat
import java.util.*

object ConvertDate {

    fun convertStringToDate(date: String): String{
        var format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val convertedDate = format.parse(date)

        format = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val currentDate = format.format(convertedDate as Date)

        return currentDate.toString()
    }
}
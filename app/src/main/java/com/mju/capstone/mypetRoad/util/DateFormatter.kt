package com.mju.capstone.mypetRoad.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateFormatter {
    fun dateToString(date: Date): String? {
        val dateString = date.toString()
        val dateTimeFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
        val localDateTime = LocalDateTime.parse(dateString, dateTimeFormatter)
        val formattedDateString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss.SSS"))

        return formattedDateString
    }

    fun formatDate(date: Calendar): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(date.time)
    }
}
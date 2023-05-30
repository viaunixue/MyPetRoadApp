package com.mju.capstone.mypetRoad.util

import android.os.Build
import androidx.annotation.RequiresApi
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateFormatter { //Date를 format에 맞게 String으로 변환
    @RequiresApi(Build.VERSION_CODES.O)
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

    fun getFirstDayOfMonth(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return calendar.time
    }

    fun getLastDayOfMonth(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        return calendar.time
    }
}
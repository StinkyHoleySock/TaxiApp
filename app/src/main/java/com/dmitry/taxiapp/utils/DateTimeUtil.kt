package com.dmitry.taxiapp.utils

import android.os.Build
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun String.formatDate(): String {
    // "2015-08-27T16:36:56+03:00"
    val inputDateFormatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss:XXXXX")
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    var date = OffsetDateTime.parse(this, inputDateFormatter)

    return this
}
package com.dmitry.taxiapp.utils


import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

private fun formatByPattern(text: String, pattern: String): String {
    val date = OffsetDateTime.parse(text, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    return DateTimeFormatter.ofPattern(pattern).format(date)
}

fun String.formatDate(): String {
    return formatByPattern(text = this, pattern = "dd-MM-yyyy")
}

fun String.formatTime(): String {
    return formatByPattern(text = this, pattern = "hh:mm:ss")
}



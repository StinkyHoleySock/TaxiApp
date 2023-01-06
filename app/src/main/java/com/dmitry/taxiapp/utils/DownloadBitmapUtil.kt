package com.dmitry.taxiapp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

fun downloadBitmap(imageUrl: String): Bitmap? {
    return try {
        val connection = URL(imageUrl).openConnection()
        connection.connect()
        val inputStream = connection.getInputStream()
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
        bitmap
    } catch (e: Exception) {
        null //fixme
    }
}
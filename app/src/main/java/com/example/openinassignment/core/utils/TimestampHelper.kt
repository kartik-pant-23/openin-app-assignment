package com.example.openinassignment.core.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object TimestampHelper {

    private const val FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DEFAULT = "dd MMM, yyyy"
    const val LONG_MONTH = "dd MMMM, yyyy"
    const val DETAILED = "dd-MM-yyyy hh:mm a"

    private fun getDateFormat(format: String): SimpleDateFormat {
        return SimpleDateFormat(format, Locale.ENGLISH)
    }

    private fun getDate(timestamp: String, format: String = FORMAT): Date? {
        return try {
            getDateFormat(format).parse(timestamp)
        } catch (e: Exception) {
            Log.e("TimestampHelper", "failed parsing timestamp - $timestamp")
            null
        }
    }

    private fun getDateString(date: Date?, format: String = FORMAT): String? {
        if (date == null)
            return null
        return try {
            getDateFormat(format).format(date)
        } catch (e: Exception) {
            Log.e("TimestampHelper", "failed formatting date - $date")
            null
        }
    }

    fun getFormattedString(timestamp: String, format: String = DEFAULT): String {
        return getDateString(getDate(timestamp), format) ?: "-"
    }

}

package com.otb.githubtracker.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Mohit Rajput on 06/08/22.
 */
object DateUtils {
    fun getFormattedTime(date: Date): String {
        val today = Calendar.getInstance()
        val formatter = SimpleDateFormat("h:mm aaa", Locale.ENGLISH)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date.time
        if (today[Calendar.YEAR] == calendar[Calendar.YEAR] && today[Calendar.DAY_OF_YEAR] == calendar[Calendar.DAY_OF_YEAR]) {
            return formatter.format(calendar.time)
        }
        today.add(Calendar.DAY_OF_YEAR, -1) //Yesterday
        if (today[Calendar.YEAR] == calendar[Calendar.YEAR] && today[Calendar.DAY_OF_YEAR] == calendar[Calendar.DAY_OF_YEAR]) {
            return "Yesterday"
        }
        today.add(Calendar.DAY_OF_YEAR, 1) //Today
        return SimpleDateFormat("d MMM yy", Locale.ENGLISH).format(calendar.time)
    }
}
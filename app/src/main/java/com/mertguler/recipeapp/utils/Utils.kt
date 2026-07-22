package com.mertguler.recipeapp.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale

fun getTodayDateString(): String {
    return SimpleDateFormat(
        "yyyy-MM-dd",
        Locale.US
    ).format(Date())
}

fun getTodayDate(): LocalDateTime {
    return LocalDateTime.now()
}
package com.app.jetpack.mvvm.common.general.extensions

import kotlin.time.Duration.Companion.minutes

fun Int.genderInString(): String {
    return when (this) {
        1 -> "Female"
        2 -> "Male"
        else -> ""
    }
}

fun Int.hourMinutes(): String {
    return "${this.minutes.inWholeHours}h ${this % 60}m"
}
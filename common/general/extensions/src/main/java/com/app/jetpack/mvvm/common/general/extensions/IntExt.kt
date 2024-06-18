package com.app.jetpack.mvvm.common.general.extensions

import kotlin.time.Duration.Companion.minutes

private const val DEFAULT_MINUTES = 60

fun Int.genderInString(): String {
    return when (this) {
        1 -> "Female"
        2 -> "Male"
        else -> ""
    }
}

fun Int.hourMinutes(): String {
    return "${this.minutes.inWholeHours}h ${this % DEFAULT_MINUTES}m"
}

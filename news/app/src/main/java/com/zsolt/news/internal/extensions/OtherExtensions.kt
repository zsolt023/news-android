package com.zsolt.news.internal.extensions

fun Long?.toTimeText(): String {
    return if (this == null || this <= 0) {
        ""
    } else {
        val it = this / 1000
        val hours = it / 3600
        val mins = it.rem(3600) / 60
        val secs = it.rem(60)
        if (hours > 0L) {
            "${hours}H ${mins}M ${secs}S"
        } else {
            "${mins}M ${secs}S"
        }
    }
}
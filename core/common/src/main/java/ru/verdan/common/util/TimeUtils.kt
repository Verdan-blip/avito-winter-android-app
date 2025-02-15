package ru.verdan.common.util

fun Long.timeToProgress(duration: Long): Int {
    return if (duration == 0L) 0 else ((this / duration.toDouble()) * 100).toInt()
}

fun Int.progressToTime(duration: Long): Long {
    return if (duration == 0L) 0 else this * duration / 100
}

fun Long.millisToMmSsString(): String {
    val minutes = this / (1000 * 60)
    val seconds = this / 1000 % 60
    return "%02d:%02d".format(minutes, seconds)
}

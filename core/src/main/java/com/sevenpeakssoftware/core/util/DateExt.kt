package com.sevenpeakssoftware.core.util

import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException

fun LocalDateTime.toFormatDateTime(): String {
    val currentYear = LocalDateTime.now().year
    val year = if (currentYear == this.year) "" else " ${this.year}"
    val minute = if (this.minute < 10) "0${this.minute}" else "${this.minute}"
    return "${this.dayOfMonth} ${this.month}$year, ${this.hour}:$minute"
}

fun String?.toArticleFormatDateTime(): LocalDateTime {
    try {
        val dateTimeFormatter = this?.let {
            if (this.contains("AM", true) || this.contains("PM", true)) {
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm a")
            } else {
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
            }
        }
        return LocalDateTime.parse(
            this,
            dateTimeFormatter
        ).atOffset(ZoneOffset.UTC).toLocalDateTime()
    } catch (e: Exception) {
        throw DateTimeParseException(e.message, "Article DateTime Pass Error", e.hashCode())
    }
}

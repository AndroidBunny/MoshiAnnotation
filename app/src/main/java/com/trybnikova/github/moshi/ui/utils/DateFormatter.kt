package com.trybnikova.github.moshi.ui.utils

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

private val DATE_FORMATTER_NO_WEEKDAY: DateTimeFormatter by lazy { DateTimeFormatter.ofPattern("dd.MM.yyyy") }

/**
 * Display the given [LocalDate] as "dd.MM.yyyy".
 */
fun LocalDate?.asDateNoWeekday(): String = this?.format(DATE_FORMATTER_NO_WEEKDAY) ?: ""

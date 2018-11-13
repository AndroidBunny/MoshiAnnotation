package com.trybnikova.github.moshi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDate


/**
 * Container for a list of tasks
 */
@JsonClass(generateAdapter = true)
data class Tasks(
    @Json(name = "total_size")
    val totalSize: Int = -1,
    @Json(name = "list")
    val taskList: List<Task>? = null
)

/**
 * A simple task item
 */
@JsonClass(generateAdapter = true)
data class Task(
    @Json(name = "id")
    val id: Int,
    @Json(name = "header")
    val header: Header
)

/**
 * Header of a task
 */
@JsonClass(generateAdapter = true)
data class Header(
    @Json(name = "date")
    val date: LocalDate,    // YYYY-MM-DD >> 2018-09-18,
    @Json(name = "version")
    val version: Int = -1,
    @Json(name = "location_info")
    val locationInfo: String = "",
    @Json(name = "done")
    val done: Boolean = false,
    @Json(name = "name")
    val name: String = ""
)

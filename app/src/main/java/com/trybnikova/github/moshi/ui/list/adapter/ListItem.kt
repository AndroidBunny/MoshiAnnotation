package com.trybnikova.github.moshi.ui.list.adapter

import org.threeten.bp.LocalDate

/**
 * Parent/Sealed class for [TaskItem]
 */
sealed class ListItem

/**
 * Model for a task item in RecyclerView
 */
data class TaskItem(
    val taskId: Int,
    val date: LocalDate,
    val version: Int,
    val location: String,
    val name: String,
    val isDone: Boolean
) : ListItem()

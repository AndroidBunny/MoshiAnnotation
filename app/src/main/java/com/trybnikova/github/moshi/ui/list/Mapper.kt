package com.trybnikova.github.moshi.ui.list

import com.trybnikova.github.moshi.model.Task
import com.trybnikova.github.moshi.ui.list.adapter.TaskItem

/**
 * Maps an [Task] to an [TaskItem]
 */
fun Task.mapToTaskItem(): TaskItem {
    return TaskItem(
        taskId = id,
        date = header.date,
        version = header.version,
        location = header.locationInfo,
        name = header.name,
        isDone = header.done
    )
}

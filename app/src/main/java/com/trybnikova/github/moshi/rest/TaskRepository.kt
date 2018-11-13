package com.trybnikova.github.moshi.rest

import com.trybnikova.github.moshi.BuildConfig
import com.trybnikova.github.moshi.model.Tasks
import io.reactivex.Single

/**
 * Repository for the rest API
 */
class TaskRepository(private val taskApi: TaskApi) {

    /**
     * Retrieves tasks via [TaskApi.getTasks]
     */
    fun getTasks(): Single<Tasks> = taskApi.getTasks(BuildConfig.CONFIG_URL)

}

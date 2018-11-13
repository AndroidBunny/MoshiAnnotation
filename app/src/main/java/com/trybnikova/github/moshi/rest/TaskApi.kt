package com.trybnikova.github.moshi.rest

import com.trybnikova.github.moshi.model.Tasks
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Contract rest API
 */
interface TaskApi {

    /**
     * Get all tasks
     */
    @GET
    fun getTasks(@Url url: String): Single<Tasks>

}

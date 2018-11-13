package com.trybnikova.github.moshi.di

import android.content.Context
import com.trybnikova.github.moshi.BuildConfig
import com.trybnikova.github.moshi.rest.TaskApi
import com.trybnikova.github.moshi.rest.TaskRepository
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

private const val HTTP_CACHE_DIRECTORY_NAME = "HttpCache"
private const val HTTP_CACHE_SIZE = 10 * 1024 * 1024L // 10 MB

val restModule = module {

    single{
        createOkHttpClient(
            context = androidContext()
        )
    }

    single {
        TaskRepository(
            taskApi = get()
        )
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TaskApi::class.java)
    }
}

private fun createOkHttpClient(context: Context): OkHttpClient {
    val cacheDir = File(context.cacheDir.absolutePath, HTTP_CACHE_DIRECTORY_NAME)
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor())
        .cache(Cache(cacheDir, HTTP_CACHE_SIZE))
        .build()
}

private fun loggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}

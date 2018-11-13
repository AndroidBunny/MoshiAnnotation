package com.trybnikova.github.moshi


import android.app.Application
import android.os.Looper
import com.jakewharton.threetenabp.AndroidThreeTen
import com.trybnikova.github.moshi.di.allKoinModules
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.android.startKoin
import timber.log.Timber

/**
 * App's application class.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin!
        startKoin(this, allKoinModules)

        // init ThreeTenABP
        AndroidThreeTen.init(this)

        RxJavaPlugins.setErrorHandler { Timber.e(it) }

        // Experimental: https://medium.com/@sweers/rxandroids-new-async-api-4ab5b3ad3e93
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            AndroidSchedulers.from(Looper.getMainLooper(), true)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

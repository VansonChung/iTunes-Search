package com.van.itunessearch

import android.app.Application
import com.van.itunessearch.log.MultiTagTree
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(MultiTagTree())
        }
    }
}
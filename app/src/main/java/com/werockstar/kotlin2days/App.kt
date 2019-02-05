package com.werockstar.kotlin2days

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class App : Application() {

    fun getBaseUrl(): String = "https://api.github.com/"

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())
    }
}
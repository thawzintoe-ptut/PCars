package com.sevenpeakssoftware.pcars

import android.app.Application
import com.sevenpeakssoftware.sync.initializers.AppInitializers
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class PCarsApp : Application() {
    @Inject lateinit var initializers: AppInitializers

    override fun onCreate() {
        super.onCreate()
        initializers.init(this)
    }
}

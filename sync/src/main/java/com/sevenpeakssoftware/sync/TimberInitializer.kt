package com.sevenpeakssoftware.sync

import android.app.Application
import com.sevenpeakssoftware.core.AppInitializer
import com.sevenpeakssoftware.core.util.Logger
import javax.inject.Inject

class TimberInitializer @Inject constructor(
    private val logger: Logger
) : AppInitializer {
    override fun init(application: Application) {
        logger.setup(BuildConfig.DEBUG)
    }
}

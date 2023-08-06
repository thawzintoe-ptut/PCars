package com.sevenpeakssoftware.sync

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sevenpeakssoftware.core.AppInitializer
import com.sevenpeakssoftware.core.CarDispatcher
import com.sevenpeakssoftware.core.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.zone.ZoneRulesProvider
import javax.inject.Inject

class ThreeTenBpInitializer @Inject constructor(
    @Dispatcher(CarDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : AppInitializer {
    override fun init(application: Application) {
        AndroidThreeTen.init(application)
        @OptIn(DelicateCoroutinesApi::class)
        GlobalScope.launch(ioDispatcher) {
            ZoneRulesProvider.getAvailableZoneIds()
        }
    }
}

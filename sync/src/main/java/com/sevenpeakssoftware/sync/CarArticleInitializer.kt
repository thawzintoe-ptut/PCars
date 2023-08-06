package com.sevenpeakssoftware.sync

import android.app.Application
import com.sevenpeakssoftware.article_domain.usecase.SyncArticlesUsecase
import com.sevenpeakssoftware.core.AppInitializer
import com.sevenpeakssoftware.core.exception.SyncDataNotSuccessException
import com.sevenpeakssoftware.core.util.Logger
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarArticleInitializer @Inject constructor(
    private val syncArticlesUsecase: SyncArticlesUsecase,
    private val logger: Logger
) : AppInitializer {
    override fun init(application: Application) {
        @OptIn(DelicateCoroutinesApi::class)
        GlobalScope.launch(Dispatchers.IO) {
            try {
                syncArticlesUsecase.execute(Unit)
            } catch (e: SyncDataNotSuccessException) {
                logger.e(e)
            }
        }
    }
}

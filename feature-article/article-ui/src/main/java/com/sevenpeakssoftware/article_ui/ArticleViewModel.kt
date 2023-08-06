package com.sevenpeakssoftware.article_ui

import androidx.compose.runtime.snapshots.MutableSnapshot
import com.mixpanel.android.mpmetrics.MixpanelAPI
import com.sevenpeakssoftware.article_domain.usecase.GetArticlesUsecase
import com.sevenpeakssoftware.article_domain.usecase.SyncArticlesUsecase
import com.sevenpeakssoftware.article_ui.vo.ArticleVOMapper
import com.sevenpeakssoftware.core.CarBaseViewModel
import com.sevenpeakssoftware.core.CarDispatcher
import com.sevenpeakssoftware.core.Dispatcher
import com.sevenpeakssoftware.core.exception.ExceptionToStringMapperImpl
import com.sevenpeakssoftware.core.exception.SyncDataNotSuccessException
import com.sevenpeakssoftware.core.util.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val getArticlesUsecase: GetArticlesUsecase,
    private val syncArticlesUsecase: SyncArticlesUsecase,
    private val exceptionMapper: ExceptionToStringMapperImpl,
    private val articleVOMapper: ArticleVOMapper,
    private val carLogger: Logger,
    @Dispatcher(CarDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val mixpanelAPI: MixpanelAPI
) : CarBaseViewModel(ioDispatcher) {

    private var _articleUiStateFlow: MutableStateFlow<ArticleUiState> =
        MutableStateFlow(ArticleUiState.Loading)
    val carStateList: StateFlow<ArticleUiState> = _articleUiStateFlow.asStateFlow()

    init {
        viewModelScopeIO {
            getCarsFromUsecase()
        }
    }

    private suspend fun getCarsFromUsecase() {
        getArticlesUsecase.execute(Unit)
            .map { carModelList ->
                if (carModelList.isEmpty()) {
                    ArticleUiState.Error(exceptionMapper.exceptionNoData())
                } else {
                    val cars = carModelList.map(articleVOMapper::map)
                    ArticleUiState.Articles(cars)
                }
            }.catch { error ->
                ArticleUiState.Error(exceptionMapper.map(error))
            }.collectLatest { carHomeUiState ->
                carLogger.d(carHomeUiState.toString())
                _articleUiStateFlow.value = carHomeUiState
            }
    }

    /**
     * function will be called when click onRetry Button
     */
    fun onTapRetry() {
        _articleUiStateFlow.value = ArticleUiState.Loading
        viewModelScopeIO {
            try {
                syncArticlesUsecase.execute(Unit).collectLatest {
                    getCarsFromUsecase()
                }
            } catch (exception: SyncDataNotSuccessException) {
                carLogger.e(exception)
                _articleUiStateFlow.value =
                    ArticleUiState.Error(exceptionMapper.map(exception))
            }
        }
    }

    /**
     * function will be called when pull refresh layout
     */
    fun onSwipeRefresh() {
        viewModelScopeIO {
            isRefreshingState.value = true
            try {
                syncArticlesUsecase.execute(Unit).collectLatest {
                    isRefreshingState.value = false
                }
            } catch (exception: SyncDataNotSuccessException) {
                isRefreshingState.value = false
                carLogger.e(exception)
            }
        }
    }

    fun getMixPanelApi(): MixpanelAPI {
        return mixpanelAPI
    }
}

package com.sevenpeakssoftware.article_ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sevenpeakssoftware.core.ui.ScreenState
import com.sevenpeakssoftware.core.ui.ScreenStateCallBack
import com.sevenpeakssoftware.core.ui.ScreenStateData

/**
 * Screen State for UI Data and Delegate
 * [@param]Data -> UI Data
 * [@param]Delegate -> UI Delegate (eg: onClick)
 */
class ArticleScreenState(
    val data: Data,
    val delegate: Delegate
) : ScreenState<ArticleScreenState>(data, delegate) {
    data class Data(
        val articleUiState: State<ArticleUiState>,
        val isRefreshingState: State<Boolean>
    ) : ScreenStateData<ArticleScreenState>

    data class Delegate(
        val onSwipeTapRefresh: () -> Unit = {},
        val onTapRetry: () -> Unit = {}
    ) : ScreenStateCallBack<ArticleScreenState>
}

/**
 * Initialize data with dynamic loading for showing design layout */
class ArticlePreviewProvider : PreviewParameterProvider<ArticleScreenState> {
    override val values: Sequence<ArticleScreenState>
        get() = sequenceOf(
            ArticleScreenState(
                data = ArticleScreenState.Data(
                    articleUiState = mutableStateOf(ArticleUiState.Error("Empty")),
                    isRefreshingState = mutableStateOf(false)
                ),
                delegate = ArticleScreenState.Delegate()
            )
        )
}

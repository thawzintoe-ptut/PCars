package com.sevenpeakssoftware.article_ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sevenpeakssoftware.article_ui.vo.ArticleVO
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.threeten.bp.LocalDateTime

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ArticleScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var articleScreenLoading: String
    private lateinit var articleErrorContent: String

    private val retryButtonMatcher by lazy {
        hasText(
            composeTestRule.activity.resources.getString(R.string.retry_btn_label)
        )
    }

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        AndroidThreeTen.init(context)
        composeTestRule.activity.apply {
            articleScreenLoading = getString(R.string.article_screen_loading)
            articleErrorContent = getString(R.string.error_content)
        }
    }

    @SuppressLint("UnrememberedMutableState")
    @Test
    fun profileLoading_whenUiStateIsLoading_showLoading() {
        composeTestRule.setContent {
            ArticleScreenStateLess(
                state = ArticleScreenState(
                    data = ArticleScreenState.Data(
                        articleUiState = mutableStateOf(ArticleUiState.Loading),
                        isRefreshingState = mutableStateOf(false)
                    ),
                    delegate = ArticleScreenState.Delegate()
                )
            )
        }

        composeTestRule
            .onNodeWithTag(articleScreenLoading)
            .assertExists()
    }

    @SuppressLint("UnrememberedMutableState")
    @Test
    fun articleScreen_whenUiStateIsError_showError() {
        composeTestRule.setContent {
            ArticleScreenStateLess(
                state = ArticleScreenState(
                    data = ArticleScreenState.Data(
                        articleUiState = mutableStateOf(
                            ArticleUiState.Error("")
                        ),
                        isRefreshingState = mutableStateOf(false)
                    ),
                    delegate = ArticleScreenState.Delegate()
                )
            )
        }
        composeTestRule
            .onNodeWithTag(articleErrorContent)
            .assertExists()
    }

    @SuppressLint("UnrememberedMutableState")
    @Test
    fun articleScreen_whenUiStateIsSuccess_showArticlesInScreen() {
        val articles = listOf(
            ArticleVO(
                publishDate = LocalDateTime.now(),
                title = TEST_TITLE,
                image = TEST_IMAGE,
                ingress = TEST_INGRESS,
                id = 1
            )
        )
        composeTestRule.setContent {
            ArticleScreenStateLess(
                state = ArticleScreenState(
                    data = ArticleScreenState.Data(
                        articleUiState = mutableStateOf(
                            ArticleUiState.Articles(articles)
                        ),
                        isRefreshingState = mutableStateOf(false)
                    ),
                    delegate = ArticleScreenState.Delegate()
                )
            )
        }

        composeTestRule
            .onNodeWithText(TEST_TITLE)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(TEST_INGRESS)
            .assertIsDisplayed()
        composeTestRule
            .onAllNodes(hasScrollToNodeAction())
            .onFirst()
            .assertIsDisplayed()
    }

    @SuppressLint("UnrememberedMutableState")
    @Test
    fun articleScreenShowError_canClick_retryButton() {
        composeTestRule.setContent {
            ArticleScreenStateLess(
                state = ArticleScreenState(
                    data = ArticleScreenState.Data(
                        articleUiState = mutableStateOf(ArticleUiState.Error("")),
                        isRefreshingState = mutableStateOf(false)
                    ),
                    delegate = ArticleScreenState.Delegate()
                )
            )
        }
        composeTestRule
            .onNodeWithTag(articleErrorContent)
            .assertExists()
        composeTestRule
            .onNode(retryButtonMatcher)
            .assertIsDisplayed()
            .assertHasClickAction()
    }
}

private const val TEST_TITLE = "Q7 - Greatness starts, when you don't stop."
private const val TEST_IMAGE = "articleImage.jpg"
private const val TEST_INGRESS = "test ingress"

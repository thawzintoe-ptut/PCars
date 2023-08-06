package com.sevenpeakssoftware.article_ui

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.mixpanel.android.mpmetrics.MixpanelAPI
import com.sevenpeakssoftware.article_ui.vo.ArticleVO
import com.sevenpeakssoftware.core.util.toFormatDateTime
import com.sevenpeakssoftware.core_ui.CenterGradientColor
import com.sevenpeakssoftware.core_ui.EndGradientColor
import com.sevenpeakssoftware.core_ui.NetworkImagePainter
import com.sevenpeakssoftware.core_ui.StartGradientColor
import com.sevenpeakssoftware.core_ui.component.PCarButton
import com.sevenpeakssoftware.core_ui.component.PCarToolbar
import com.sevenpeakssoftware.core_ui.component.ShimmerLoading
import com.sevenpeakssoftware.core_ui.component.linearGradientAngle
import com.sevenpeakssoftware.core_ui.dateTextStyle
import com.sevenpeakssoftware.core_ui.normalTextStyle
import com.sevenpeakssoftware.core_ui.titleTextStyle
import org.json.JSONObject
import org.threeten.bp.LocalDateTime

@Composable
fun ArticleScreen(
    viewModel: ArticleViewModel = hiltViewModel(),
) {
    setUpMixPanelAnalytics(viewModel.getMixPanelApi())
    ArticleScreenStateLess(
        state = ArticleScreenState(
            data = ArticleScreenState.Data(
                articleUiState = viewModel.carStateList.collectAsState(),
                isRefreshingState = viewModel.isRefreshingState.collectAsState(),
            ),
            delegate = ArticleScreenState.Delegate(
                onSwipeTapRefresh = viewModel::onSwipeRefresh,
                onTapRetry = viewModel::onTapRetry,
            ),
        ),
    )

}

private fun setUpMixPanelAnalytics(mixpanelApi: MixpanelAPI) {
    mixpanelApi.identify("thaw.toe@7peakssoftware.com")
    /**
     * Event Properties:
     * Event properties are the attributes associated with a specific event. In this case, the event is a user signup.
     */
    val signUpEventProps = JSONObject().apply {
        put("CarViewed", "Porsche")
        put("Login Date", "${LocalDateTime.now()}")
        put("Login Source", "SevenPeaks")
        put("Login Device", "Android")
    }
    mixpanelApi.track("User Login", signUpEventProps)

    /**
     * User Properties:
     * User properties are attributes associated with a specific user in your system.
     */
    val signUpUserProps = JSONObject().apply {
        put("email", "thaw.toe@7peakssoftware.com")
        put("name", "Thaw Zin Toe")
        put("gender", "male")
        put("age", "27")
    }
    mixpanelApi.people.set(signUpUserProps)

    /**
     * Super Properties:
     * Super properties are global properties that are associated with all events and users in your system.
     */
    val signUpSuperProps = JSONObject().apply {
        put("Android Version", "${Build.VERSION.BASE_OS}")
        put("Referral Source", "Seven Peaks")
        put("Currency", "THB")
        put("Language", "English")
    }
    mixpanelApi.registerSuperProperties(signUpSuperProps)
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenStateLess(
    @PreviewParameter(ArticlePreviewProvider::class)
    state: ArticleScreenState,
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            // We use TopAppBar from accompanist-insets-ui which allows us to provide
            // content padding matching the system bars insets.
            PCarToolbar(
                title = stringResource(R.string.car_home_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
            )
        },
        bottomBar = {
            // We add a spacer as a bottom bar, which is the same height as
            // the navigation bar
            Spacer(Modifier.navigationBarsPadding())
        },
    ) { contentPadding ->
        when (val uiState = state.data.articleUiState.value) {
            is ArticleUiState.Loading ->
                ShimmerLoading(
                    modifier = Modifier
                        .padding(contentPadding)
                        .testTag(stringResource(R.string.article_screen_loading)),
                )

            is ArticleUiState.Error ->
                EmptyArticleContent(
                    modifier = Modifier.padding(contentPadding),
                    message = uiState.errorMessage,
                    onRetry = state.delegate.onTapRetry,
                )

            is ArticleUiState.Articles ->
                ArticleContent(
                    modifier = Modifier.padding(contentPadding),
                    articles = uiState.items,
                    onSwipeRefresh = state.delegate.onSwipeTapRefresh,
                    isRefreshing = state.data.isRefreshingState.value,
                )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleContent(
    modifier: Modifier,
    articles: List<ArticleVO>,
    onSwipeRefresh: () -> Unit,
    isRefreshing: Boolean,
) {
    /**
     * Lazy List State for LazyColumn
     */
    val lazyListState = rememberLazyListState()

    /**
     * pullRefreshState for pull refresh layout
     */
    val pullRefreshState = rememberPullRefreshState(
        isRefreshing,
        onSwipeRefresh,
    )
    /**
     * Car View Object List
     */
    Box(
        modifier = modifier
            .pullRefresh(pullRefreshState),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            state = lazyListState,
        ) {
            items(articles, key = { it.id }) {
                ArticleItem(it)
            }
        }
        /**
         * pull to refresh indicator
         */
        PullRefreshIndicator(
            isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter),
        )
    }
}

@Composable
fun ArticleItem(
    car: ArticleVO,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                brush = Brush.linearGradientAngle(
                    0.1205f to StartGradientColor,
                    0.4f to CenterGradientColor,
                    0.6f to EndGradientColor,
                    angleInDegrees = 270f,
                ),
                shape = RectangleShape,
            ),
    ) {
        Column {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp),
            ) {
                val (image, title) = createRefs()
                NetworkImagePainter(
                    url = car.image,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        },
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.linearGradientAngle(
                                0.1205f to StartGradientColor,
                                0.8f to CenterGradientColor,
                                angleInDegrees = 270f,
                            ),
                            shape = RectangleShape,
                        )
                        .constrainAs(title) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(parent.top)
                            height = Dimension.fillToConstraints
                        },
                ) {
                    Text(
                        text = car.title,
                        style = titleTextStyle(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .align(Alignment.BottomStart),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Text(
                text = car.publishDate.toFormatDateTime(),
                style = dateTextStyle(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            )
            Text(
                text = car.ingress,
                style = normalTextStyle(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 11.dp),
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun EmptyArticleContent(
    modifier: Modifier,
    message: String,
    onRetry: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .testTag(stringResource(R.string.error_content)),
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
        ) {
            Image(
                painter = painterResource(
                    id = com.sevenpeakssoftware.core_ui.R.drawable.ic_all_inbox,
                ),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
            )
            Text(
                text = message,
                style = titleTextStyle(),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.please_try_again_label),
                style = dateTextStyle(),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            PCarButton(
                text = stringResource(R.string.retry_btn_label),
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = onRetry,
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun PreviewArticleItem() {
    ArticleItem(car = ArticleVO.dummy)
}

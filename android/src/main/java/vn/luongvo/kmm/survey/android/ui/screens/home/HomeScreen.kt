package vn.luongvo.kmm.survey.android.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import vn.luongvo.kmm.survey.android.ui.common.DimmedImageBackground
import vn.luongvo.kmm.survey.android.ui.common.RtlModalDrawer
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.preview.*
import vn.luongvo.kmm.survey.android.ui.screens.home.views.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.colors
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme
import vn.luongvo.kmm.survey.android.util.userReadableMessage

const val HomeUserAvatar = "HomeUserAvatar"
const val HomeSurveyDetail = "HomeSurveyDetail"

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    navigator: (destination: AppDestination) -> Unit
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val currentDate by viewModel.currentDate.collectAsStateWithLifecycle()
    val user by viewModel.user.collectAsStateWithLifecycle()
    val surveys by viewModel.surveys.collectAsStateWithLifecycle()
    val appVersion by viewModel.appVersion.collectAsStateWithLifecycle()

    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    error?.let {
        scope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message = it.userReadableMessage(context))
        }
        viewModel.clearError()
    }

    LaunchedEffect(viewModel.navigator) {
        viewModel.navigator.collect { destination -> navigator(destination) }
    }

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    HomeScreenWithDrawer(
        appVersion = appVersion,
        scaffoldState = scaffoldState,
        isLoading = isLoading,
        isRefreshing = isRefreshing,
        currentDate = currentDate,
        user = user,
        surveys = surveys,
        onSurveyClick = { survey -> viewModel.navigateToSurvey(survey?.id.orEmpty()) },
        onRefresh = { viewModel.loadData(isRefresh = true) },
        onMenuLogoutClick = { viewModel.logOut() }
    )
}

@Composable
private fun HomeScreenWithDrawer(
    appVersion: String,
    initialDrawerState: DrawerValue = DrawerValue.Closed,
    scaffoldState: ScaffoldState,
    isLoading: Boolean,
    isRefreshing: Boolean,
    currentDate: String,
    user: UserUiModel?,
    surveys: List<SurveyUiModel>,
    onSurveyClick: (SurveyUiModel?) -> Unit,
    onRefresh: () -> Unit,
    onMenuLogoutClick: () -> Unit
) {
    val drawerState = rememberDrawerState(initialDrawerState)
    val scope = rememberCoroutineScope()

    RtlModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            HomeDrawer(
                user = user,
                appVersion = appVersion,
                onLogoutClick = {
                    scope.launch { drawerState.close() }
                    onMenuLogoutClick()
                }
            )
        }
    ) {
        HomeScreenContent(
            scaffoldState = scaffoldState,
            isLoading = isLoading,
            isRefreshing = isRefreshing,
            currentDate = currentDate,
            user = user,
            surveys = surveys,
            onSurveyClick = onSurveyClick,
            onUserAvatarClick = {
                scope.launch { drawerState.open() }
            },
            onRefresh = onRefresh
        )
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
private fun HomeScreenContent(
    scaffoldState: ScaffoldState,
    isLoading: Boolean,
    isRefreshing: Boolean,
    currentDate: String,
    user: UserUiModel?,
    surveys: List<SurveyUiModel>,
    onSurveyClick: (SurveyUiModel?) -> Unit,
    onUserAvatarClick: () -> Unit,
    onRefresh: () -> Unit
) {
    val refreshingState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = onRefresh
    )
    val pagerState = rememberPagerState()
    var survey by remember { mutableStateOf<SurveyUiModel?>(null) }

    LaunchedEffect(surveys) {
        snapshotFlow { pagerState.currentPage }.collect { index ->
            survey = surveys.getOrNull(index)
        }
    }

    Scaffold(scaffoldState = scaffoldState) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(refreshingState)
                .padding(padding)
        ) {
            // pullRefresh needs a vertical scroll component to work
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Box(modifier = Modifier.fillParentMaxHeight()) {
                        HorizontalPager(
                            count = surveys.size,
                            state = pagerState,
                            modifier = Modifier.fillMaxSize()
                        ) { index ->
                            DimmedImageBackground(
                                imageUrl = surveys[index].coverImageUrl
                            )
                        }

                        HomeHeader(
                            isLoading = isLoading,
                            dateTime = currentDate,
                            user = user,
                            onUserAvatarClick = onUserAvatarClick,
                            modifier = Modifier
                                .statusBarsPadding()
                                .padding(top = dimensions.paddingSmall)
                        )

                        HomeFooter(
                            pagerState = pagerState,
                            isLoading = isLoading,
                            survey = survey,
                            modifier = Modifier
                                .navigationBarsPadding()
                                .align(Alignment.BottomCenter)
                                .padding(bottom = dimensions.paddingHuge),
                            onSurveyClick = onSurveyClick
                        )
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = refreshingState,
                backgroundColor = colors.pullRefreshBackground,
                contentColor = colors.pullRefreshContent,
                modifier = Modifier.align(alignment = Alignment.TopCenter)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(
    @PreviewParameter(HomeParameterProvider::class) params: HomeParameterProvider.Params
) {
    with(params) {
        ComposeTheme {
            HomeScreenWithDrawer(
                appVersion = appVersion,
                initialDrawerState = drawerState,
                scaffoldState = rememberScaffoldState(),
                isLoading = isLoading,
                isRefreshing = isLoading,
                currentDate = currentDate,
                user = user,
                surveys = surveys,
                onSurveyClick = {},
                onRefresh = {},
                onMenuLogoutClick = {}
            )
        }
    }
}

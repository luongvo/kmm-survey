package vn.luongvo.kmm.survey.android.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.*
import org.koin.androidx.compose.getViewModel
import vn.luongvo.kmm.survey.android.ui.common.DimmedImageBackground
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.providers.LoadingParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.home.views.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme
import vn.luongvo.kmm.survey.android.util.userReadableMessage

const val HomeUserAvatar = "HomeUserAvatar"
const val HomeSurveyDetail = "HomeSurveyDetail"

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    navigator: (destination: AppDestination) -> Unit,
    onDrawerUiStateChange: (UserUiModel?) -> Unit,
    onOpenDrawer: () -> Unit
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val currentDate by viewModel.currentDate.collectAsStateWithLifecycle()
    val user by viewModel.user.collectAsStateWithLifecycle()
    val surveys by viewModel.surveys.collectAsStateWithLifecycle()

    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    LaunchedEffect(error) {
        error?.let {
            scaffoldState.snackbarHostState.showSnackbar(message = it.userReadableMessage(context))
            viewModel.clearError()
        }
    }

    LaunchedEffect(viewModel.navigator) {
        viewModel.navigator.collect { destination -> navigator(destination) }
    }

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    onDrawerUiStateChange(user)

    HomeScreenContent(
        scaffoldState = scaffoldState,
        isLoading = isLoading,
        currentDate = currentDate,
        user = user,
        surveys = surveys,
        onSurveyClick = { survey -> viewModel.navigateToSurvey(survey?.id.orEmpty()) },
        onUserAvatarClick = onOpenDrawer
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomeScreenContent(
    scaffoldState: ScaffoldState,
    isLoading: Boolean,
    currentDate: String,
    user: UserUiModel?,
    surveys: List<SurveyUiModel>,
    onSurveyClick: (SurveyUiModel?) -> Unit,
    onUserAvatarClick: () -> Unit
) {
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
                .padding(padding)
        ) {
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
                    .padding(bottom = dimensions.paddingLargest),
                onSurveyClick = onSurveyClick
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(
    @PreviewParameter(LoadingParameterProvider::class) isLoading: Boolean
) {
    ComposeTheme {
        HomeScreenContent(
            scaffoldState = rememberScaffoldState(),
            isLoading = isLoading,
            currentDate = "Monday, JUNE 15",
            user = UserUiModel(
                email = "luong@nimblehq.co",
                name = "Luong",
                avatarUrl = "https://secure.gravatar.com/avatar/8fae17b9d0c4cca18a9661bcdf650f23"
            ),
            surveys = listOf(
                SurveyUiModel(
                    id = "1",
                    title = "Scarlett Bangkok",
                    description = "We'd love to hear from you!",
                    coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_"
                ),
                SurveyUiModel(
                    id = "2",
                    title = "ibis Bangkok Riverside",
                    description = "We'd love to hear from you!",
                    coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/287db81c5e4242412cc0_"
                )
            ),
            onSurveyClick = {},
            onUserAvatarClick = {}
        )
    }
}

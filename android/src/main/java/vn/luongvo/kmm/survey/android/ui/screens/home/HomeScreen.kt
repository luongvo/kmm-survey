package vn.luongvo.kmm.survey.android.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.*
import org.koin.androidx.compose.getViewModel
import vn.luongvo.kmm.survey.android.ui.common.DimmedImageBackground
import vn.luongvo.kmm.survey.android.ui.screens.home.views.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme
import vn.luongvo.kmm.survey.android.util.userReadableMessage

const val HomeUserAvatar = "HomeUserAvatar"

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel()
) {
    val currentDate by viewModel.currentDate.collectAsStateWithLifecycle()
    val avatarUrl by viewModel.avatarUrl.collectAsStateWithLifecycle()

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    LaunchedEffect(viewModel.error) {
        viewModel.error?.let {
            scaffoldState.snackbarHostState.showSnackbar(
                message = it.userReadableMessage(context)
            )
            viewModel.error = null
        }
    }

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    HomeScreenContent(
        scaffoldState = scaffoldState,
        currentDate = currentDate,
        avatarUrl = avatarUrl,
        // TODO Integrate in https://github.com/luongvo/kmm-survey/issues/16
        surveys = listOf(
            SurveyUiModel(
                title = "Scarlett Bangkok",
                description = "We'd love to hear from you!",
                imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_"
            ),
            SurveyUiModel(
                title = "ibis Bangkok Riverside",
                description = "We'd love to hear from you!",
                imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/287db81c5e4242412cc0_"
            )
        )
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomeScreenContent(
    scaffoldState: ScaffoldState,
    currentDate: String,
    avatarUrl: String,
    surveys: List<SurveyUiModel>
) {
    val pagerState = rememberPagerState()
    var surveyTitle by remember { mutableStateOf("") }
    var surveyDescription by remember { mutableStateOf("") }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { index ->
            surveyTitle = surveys[index].title
            surveyDescription = surveys[index].description
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
                    imageUrl = surveys[index].imageUrl
                )
            }

            HomeHeader(
                dateTime = currentDate,
                avatarUrl = avatarUrl,
                modifier = Modifier.statusBarsPadding()
            )

            HomeFooter(
                pagerState = pagerState,
                title = surveyTitle,
                description = surveyDescription,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = AppTheme.dimensions.paddingMedium)
                    .padding(bottom = 54.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    ComposeTheme {
        HomeScreenContent(
            scaffoldState = rememberScaffoldState(),
            currentDate = "Monday, JUNE 15",
            avatarUrl = "https://secure.gravatar.com/avatar/8fae17b9d0c4cca18a9661bcdf650f23",
            surveys = listOf(
                SurveyUiModel(
                    title = "Scarlett Bangkok",
                    description = "We'd love to hear from you!",
                    imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_"
                ),
                SurveyUiModel(
                    title = "ibis Bangkok Riverside",
                    description = "We'd love to hear from you!",
                    imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/287db81c5e4242412cc0_"
                )
            )
        )
    }
}

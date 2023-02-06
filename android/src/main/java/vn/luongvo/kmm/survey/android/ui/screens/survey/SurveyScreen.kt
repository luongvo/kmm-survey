package vn.luongvo.kmm.survey.android.ui.screens.survey

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import vn.luongvo.kmm.survey.android.ui.common.*
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.preview.*
import vn.luongvo.kmm.survey.android.ui.screens.home.SurveyUiModel
import vn.luongvo.kmm.survey.android.ui.screens.survey.views.SurveyIntro
import vn.luongvo.kmm.survey.android.ui.screens.survey.views.SurveyQuestion
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme
import vn.luongvo.kmm.survey.android.util.userReadableMessage

const val SurveyBackButton = "SurveyBackButton"
const val SurveyCloseButton = "SurveyCloseButton"
const val SurveyNextButton = "SurveyNextButton"

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SurveyScreen(
    surveyId: String,
    viewModel: SurveyViewModel = getViewModel(),
    navigator: (destination: AppDestination) -> Unit
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val survey by viewModel.survey.collectAsStateWithLifecycle()

    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    error?.let {
        scope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message = it.userReadableMessage(context))
        }
        viewModel.clearError()
    }

    LaunchedEffect(Unit) {
        viewModel.getSurveyDetail(id = surveyId)
    }

    SurveyScreenContent(
        scaffoldState = scaffoldState,
        isLoading = isLoading,
        survey = survey,
        onBackClick = { navigator(AppDestination.Up) },
        onSubmitClick = { viewModel.submitSurvey() }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun SurveyScreenContent(
    scaffoldState: ScaffoldState,
    isLoading: Boolean,
    survey: SurveyUiModel?,
    onBackClick: () -> Unit,
    onSubmitClick: () -> Unit
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) { padding ->
        survey?.let {
            val questions = survey.questions
            HorizontalPager(
                count = questions.size,
                state = pagerState,
                userScrollEnabled = false,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) { index ->
                // TODO use question.displayType instead
                val questionCount = questions.size - 1
                if (index == 0) {
                    SurveyIntro(
                        survey = survey,
                        onBackClick = onBackClick,
                        onStartClick = { pagerState.scrollToNextPage(scope) }
                    )
                } else {
                    SurveyQuestion(
                        index = index,
                        count = questionCount,
                        question = questions[index],
                        onCloseClick = onBackClick,
                        onNextClick = { pagerState.scrollToNextPage(scope) },
                        onSubmitClick = onSubmitClick
                    )
                }
            }
        }
    }
    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
private fun PagerState.scrollToNextPage(scope: CoroutineScope) {
    scope.launch {
        animateScrollToPage(currentPage + 1)
    }
}

@Preview(showSystemUi = true)
@Composable
fun SurveyScreenPreview(
    @PreviewParameter(SurveyDetailScreenParameterProvider::class) params: SurveyDetailParameterProvider.Params
) {
    with(params) {
        ComposeTheme {
            SurveyScreenContent(
                scaffoldState = rememberScaffoldState(),
                isLoading = isLoading,
                survey = survey,
                onBackClick = {},
                onSubmitClick = {}
            )
        }
    }
}

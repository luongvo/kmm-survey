package vn.luongvo.kmm.survey.android.ui.screens.survey

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import vn.luongvo.kmm.survey.android.ui.common.*
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.screens.home.SurveyUiModel
import vn.luongvo.kmm.survey.android.ui.screens.survey.views.SurveyIntro
import vn.luongvo.kmm.survey.android.ui.screens.survey.views.SurveyQuestion
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

const val SurveyBackButton = "SurveyBackButton"

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SurveyScreen(
    surveyId: String,
    viewModel: SurveyViewModel = getViewModel(),
    navigator: (destination: AppDestination) -> Unit
) {
    val survey by viewModel.survey.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getSurveyDetail(id = surveyId)
    }

    survey?.let {
        SurveyScreenContent(
            survey = it,
            onBackClick = { navigator(AppDestination.Up) },
            onSubmitClick = {
                // TODO submit survey
            }
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun SurveyScreenContent(
    survey: SurveyUiModel,
    onBackClick: () -> Unit,
    onSubmitClick: () -> Unit
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val questions = survey.questions
    HorizontalPager(
        count = questions.size,
        state = pagerState,
        userScrollEnabled = false,
        modifier = Modifier.fillMaxSize()
    ) { index ->
        // TODO use question.displayType instead
        if (index == 0) {
            SurveyIntro(
                survey = survey,
                onBackClick = onBackClick,
                onStartClick = { pagerState.scrollToNextPage(scope) }
            )
        } else {
            SurveyQuestion(
                index = index,
                count = questions.size - 1,
                question = questions[index],
                onCloseClick = onBackClick,
                onNextClick = {
                    if (index != questions.size) {
                        pagerState.scrollToNextPage(scope)
                    } else {
                        onSubmitClick()
                    }
                }
            )
        }
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
fun SurveyScreenPreview() {
    ComposeTheme {
        SurveyScreenContent(
            survey = SurveyUiModel(
                id = "1",
                title = "Scarlett Bangkok",
                description = "We'd love to hear from you!",
                coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
                questions = listOf(
                    QuestionUiModel(
                        text = "How fulfilled did you feel during this WFH period?",
                        coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_l"
                    )
                )
            ),
            onBackClick = {},
            onSubmitClick = {}
        )
    }
}

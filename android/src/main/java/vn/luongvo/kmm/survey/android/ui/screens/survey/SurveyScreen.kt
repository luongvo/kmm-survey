package vn.luongvo.kmm.survey.android.ui.screens.survey

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import vn.luongvo.kmm.survey.android.ui.common.*
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.screens.survey.views.SurveyIntro
import vn.luongvo.kmm.survey.android.ui.screens.survey.views.SurveyQuestion
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

const val SurveyBackButton = "SurveyBackButton"

@Composable
fun SurveyScreen(
    surveyId: String,
    viewModel: SurveyViewModel = getViewModel(),
    navigator: (destination: AppDestination) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getSurveyDetail(id = surveyId)
    }

    SurveyScreenContent(
        // TODO fake data, implement in https://github.com/luongvo/kmm-survey/pull/69
        questions = listOf(
            QuestionUiModel(text = "How fulfilled did you feel during this WFH period?"),
            QuestionUiModel(text = "How did WFH change your productivity?"),
            QuestionUiModel(text = "I have a separate space to work (room or living room)."),
            QuestionUiModel(text = "Question NPS"),
            QuestionUiModel(text = "Your contact information")
        ),
        onBackClick = { navigator(AppDestination.Up) },
        onSubmitClick = {
            // TODO submit survey
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun SurveyScreenContent(
    questions: List<QuestionUiModel>,
    onBackClick: () -> Unit,
    onSubmitClick: () -> Unit
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    HorizontalPager(
        count = questions.size + 1,
        state = pagerState,
        userScrollEnabled = false,
        modifier = Modifier.fillMaxSize()
    ) { index ->
        // TODO use question.displayType instead
        if (index == 0) {
            SurveyIntro(
                onBackClick = onBackClick,
                onStartClick = { pagerState.scrollToNextPage(scope) }
            )
        } else {
            SurveyQuestion(
                index = index,
                count = questions.size,
                question = questions[index - 1],
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
            questions = listOf(
                QuestionUiModel(text = "How fulfilled did you feel during this WFH period?")
            ),
            onBackClick = {},
            onSubmitClick = {}
        )
    }
}

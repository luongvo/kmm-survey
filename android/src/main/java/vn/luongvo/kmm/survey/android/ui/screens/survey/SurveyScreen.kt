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
        onBackClick = { navigator(AppDestination.Up) }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun SurveyScreenContent(
    onBackClick: () -> Unit
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    HorizontalPager(
        count = 5,
        state = pagerState,
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
                onCloseClick = onBackClick,
                onNextClick = { pagerState.scrollToNextPage(scope) }
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
            onBackClick = {}
        )
    }
}

package vn.luongvo.kmm.survey.android.ui.screens.home.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import vn.luongvo.kmm.survey.android.ui.common.DimmedImageBackground
import vn.luongvo.kmm.survey.android.ui.theme.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeSurveys(
    surveys: List<HomeSurveyUiModel>
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

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = surveys.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { index ->
            DimmedImageBackground(
                imageUrl = surveys[index].imageUrl
            )
        }

        HomeFooter(
            pagerState = pagerState,
            title = surveyTitle,
            description = surveyDescription,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = dimensions.paddingMedium)
                .padding(bottom = 54.dp)
        )
    }
}

data class HomeSurveyUiModel(
    val title: String,
    val description: String,
    val imageUrl: String
)

@Preview
@Composable
fun HomeSurveysPreview() {
    ComposeTheme {
        HomeSurveys(
            listOf(
                HomeSurveyUiModel(
                    title = "Scarlett Bangkok",
                    description = "We'd love ot hear from you!",
                    imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_"
                ),
                HomeSurveyUiModel(
                    title = "ibis Bangkok Riverside",
                    description = "We'd love to hear from you!",
                    imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/287db81c5e4242412cc0_"
                )
            )
        )
    }
}

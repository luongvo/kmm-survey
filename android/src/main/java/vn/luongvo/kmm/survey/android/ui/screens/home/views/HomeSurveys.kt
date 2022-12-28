package vn.luongvo.kmm.survey.android.ui.screens.home.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.*
import vn.luongvo.kmm.survey.android.ui.common.DimmedImageBackground
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

data class HomeSurveyUiModel(
    val title: String,
    val description: String,
    val imageUrl: String
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeSurveys(
    surveys: List<HomeSurveyUiModel>
) {
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = surveys.size,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { index ->
        DimmedImageBackground(
            imageUrl = surveys[index].imageUrl
        )
    }
}

@Composable
@Preview
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

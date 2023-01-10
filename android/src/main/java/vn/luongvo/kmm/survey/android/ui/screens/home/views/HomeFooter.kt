package vn.luongvo.kmm.survey.android.ui.screens.home.views

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import vn.luongvo.kmm.survey.android.extension.placeholder
import vn.luongvo.kmm.survey.android.ui.common.NextCircleButton
import vn.luongvo.kmm.survey.android.ui.preview.LoadingParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.home.HomeSurveyDetail
import vn.luongvo.kmm.survey.android.ui.screens.home.SurveyUiModel
import vn.luongvo.kmm.survey.android.ui.theme.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeFooter(
    pagerState: PagerState,
    isLoading: Boolean,
    survey: SurveyUiModel?,
    modifier: Modifier = Modifier,
    onSurveyClick: (SurveyUiModel?) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.paddingMedium)
    ) {
        if (isLoading) {
            HomeFooterLoadingContent()
        } else {
            HomeFooterContent(
                pagerState = pagerState,
                survey = survey,
                onSurveyClick = onSurveyClick
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomeFooterContent(
    pagerState: PagerState,
    survey: SurveyUiModel?,
    onSurveyClick: (SurveyUiModel?) -> Unit
) {
    HorizontalPagerIndicator(
        pagerState = pagerState,
        activeColor = White,
        inactiveColor = White20,
        modifier = Modifier.padding(vertical = dimensions.paddingLarge),
    )
    Crossfade(targetState = survey?.title) {
        Text(
            text = it.orEmpty(),
            color = White,
            style = typography.h5,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Crossfade(
            targetState = survey?.description,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = it.orEmpty(),
                color = White70,
                style = typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.width(dimensions.paddingMedium))
        NextCircleButton(
            onClick = { onSurveyClick(survey) },
            modifier = Modifier.semantics { contentDescription = HomeSurveyDetail }
        )
    }
}

@Composable
private fun HomeFooterLoadingContent() {
    Spacer(
        modifier = Modifier
            .size(30.dp, 14.dp)
            .placeholder(true)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Spacer(
        modifier = Modifier
            .size(240.dp, 18.dp)
            .placeholder(true)
    )
    Spacer(modifier = Modifier.height(5.dp))
    Spacer(
        modifier = Modifier
            .size(120.dp, 18.dp)
            .placeholder(true)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Spacer(
        modifier = Modifier
            .size(300.dp, 18.dp)
            .placeholder(true)
    )
    Spacer(modifier = Modifier.height(5.dp))
    Spacer(
        modifier = Modifier
            .size(200.dp, 18.dp)
            .placeholder(true)
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun HomeFooterPreview(
    @PreviewParameter(LoadingParameterProvider::class) isLoading: Boolean
) {
    ComposeTheme {
        HomeFooter(
            pagerState = rememberPagerState(),
            isLoading = isLoading,
            survey = SurveyUiModel(
                id = "1",
                title = "Scarlett Bangkok",
                description = "We'd love to hear from you!",
                coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_"
            )
        ) {}
    }
}

package vn.luongvo.kmm.survey.android.ui.screens.home.views

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import vn.luongvo.kmm.survey.android.ui.common.NextCircleButton
import vn.luongvo.kmm.survey.android.ui.screens.home.HomeSurveyDetail
import vn.luongvo.kmm.survey.android.ui.theme.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeFooter(
    pagerState: PagerState,
    title: String,
    description: String,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = White,
            inactiveColor = White20,
            modifier = Modifier.padding(vertical = dimensions.paddingLarge),
        )
        Crossfade(targetState = title) {
            Text(
                text = it,
                color = White,
                style = AppTheme.typography.h5,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Crossfade(
                targetState = description,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = it,
                    color = White70,
                    style = AppTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(dimensions.paddingMedium))
            NextCircleButton(
                onClick = {
                    // TODO navigate to Survey Detail screen
                },
                contentDescription = HomeSurveyDetail
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun HomeFooterPreview() {
    ComposeTheme {
        HomeFooter(
            pagerState = rememberPagerState(),
            title = "ibis Bangkok Riverside",
            description = "We'd love to hear from you!",
            modifier = Modifier.wrapContentHeight()
        )
    }
}

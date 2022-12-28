package vn.luongvo.kmm.survey.android.ui.screens.home.views

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.common.DimmedImageBackground
import vn.luongvo.kmm.survey.android.ui.theme.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography

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
            title = surveyTitle,
            description = surveyDescription,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 54.dp)
        )
    }
}

@Composable
private fun HomeFooter(
    title: String,
    description: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .padding(horizontal = dimensions.paddingMedium)
    ) {
        Crossfade(targetState = title) {
            Text(
                text = it,
                color = White,
                style = typography.h5,
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
                    style = typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(dimensions.paddingMedium))
            Button(
                onClick = {
                    // TODO navigate to Survey Detail screen
                },
                modifier = Modifier.size(dimensions.buttonHeight),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = White
                ),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

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

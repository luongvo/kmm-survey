package vn.luongvo.kmm.survey.android.ui.screens.survey

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.common.DimmedImageBackground
import vn.luongvo.kmm.survey.android.ui.common.PrimaryButton
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme
import vn.luongvo.kmm.survey.android.ui.theme.White70

const val SurveyBackButton = "SurveyBackButton"

@Composable
fun SurveyScreen(
    surveyId: String,
    navigator: (destination: AppDestination) -> Unit
) {
    SurveyScreenContent(
        onBackClick = { navigator(AppDestination.Up) },
        onStartClick = {
            // TODO start survey
        }
    )
}

@Composable
private fun SurveyScreenContent(
    onBackClick: () -> Unit,
    onStartClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        DimmedImageBackground(
            // TODO fetch survey detail https://github.com/luongvo/kmm-survey/issues/23
            imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_l"
        )

        Button(
            onClick = onBackClick,
            modifier = Modifier
                .statusBarsPadding()
                .wrapContentSize()
                .rotate(180f) // switch Right Arrow to Back button
                .padding(vertical = 5.dp)
                .semantics { this.contentDescription = SurveyBackButton },
            contentPadding = PaddingValues(dimensions.paddingMedium),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Transparent
            ),
            elevation = null
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null,
                colorFilter = ColorFilter.tint(White),
                contentScale = ContentScale.FillWidth
            )
        }

        Column(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .fillMaxWidth()
                .padding(top = 70.dp)
                .padding(horizontal = dimensions.paddingMedium)
        ) {
            Text(
                // TODO fetch survey detail https://github.com/luongvo/kmm-survey/issues/23
                text = "Scarlett Bangkok",
                color = White,
                style = typography.h4,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(dimensions.paddingSmall))
            Text(
                // TODO fetch survey detail https://github.com/luongvo/kmm-survey/issues/23
                text = "We'd love to hear from you!",
                color = White70,
                style = typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            PrimaryButton(
                text = stringResource(id = R.string.survey_start),
                onClick = onStartClick,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.End)
                    .padding(bottom = dimensions.paddingLargest)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SurveyScreenPreview() {
    ComposeTheme {
        SurveyScreenContent(
            onBackClick = {},
            onStartClick = {}
        )
    }
}

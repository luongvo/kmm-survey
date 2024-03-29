package vn.luongvo.kmm.survey.android.ui.screens.survey.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.common.*
import vn.luongvo.kmm.survey.android.ui.preview.SurveyDetailParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.home.SurveyUiModel
import vn.luongvo.kmm.survey.android.ui.screens.survey.SurveyBackButton
import vn.luongvo.kmm.survey.android.ui.theme.*

@Composable
fun SurveyIntro(
    survey: SurveyUiModel,
    onBackClick: () -> Unit,
    onStartClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        DimmedImageBackground(
            imageUrl = survey.coverImageUrl
        )

        BackButton(
            modifier = Modifier
                .statusBarsPadding()
                .padding(vertical = 5.dp)
                .semantics { contentDescription = SurveyBackButton },
            onClick = onBackClick
        )

        Column(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .fillMaxWidth()
                .padding(top = 70.dp)
                .padding(horizontal = AppTheme.dimensions.paddingMedium)
        ) {
            Text(
                text = survey.title,
                color = Color.White,
                style = AppTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(AppTheme.dimensions.paddingSmall))
            Text(
                text = survey.description,
                color = White70,
                style = AppTheme.typography.body1
            )
            Spacer(modifier = Modifier.weight(1f))
            PrimaryButton(
                text = stringResource(id = R.string.survey_start),
                onClick = onStartClick,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.End)
                    .padding(bottom = AppTheme.dimensions.paddingHuge)
            )
        }
    }
}

@Preview
@Composable
fun SurveyIntroPreview(
    @PreviewParameter(SurveyDetailParameterProvider::class) params: SurveyDetailParameterProvider.Params
) {
    ComposeTheme {
        SurveyIntro(
            survey = params.survey,
            onBackClick = {},
            onStartClick = {}
        )
    }
}

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
import androidx.compose.ui.unit.dp
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.common.*
import vn.luongvo.kmm.survey.android.ui.screens.survey.*
import vn.luongvo.kmm.survey.android.ui.theme.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions

@Composable
fun SurveyQuestion(
    index: Int,
    count: Int,
    question: QuestionUiModel,
    onCloseClick: () -> Unit,
    onNextClick: () -> Unit,
    onSubmitClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        DimmedImageBackground(
            imageUrl = question.coverImageUrl
        )

        CloseButton(
            modifier = Modifier
                .statusBarsPadding()
                .align(Alignment.TopEnd)
                .padding(vertical = dimensions.paddingMedium, horizontal = dimensions.paddingSmall)
                .semantics { contentDescription = SurveyCloseButton + index },
            onClick = onCloseClick
        )

        Column(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .fillMaxWidth()
                .padding(top = 70.dp)
                .padding(horizontal = dimensions.paddingMedium)
        ) {
            Text(
                text = "$index/$count",
                color = White50,
                style = AppTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(dimensions.paddingTiny))
            Text(
                text = question.text,
                color = Color.White,
                style = AppTheme.typography.h4
            )
            Spacer(modifier = Modifier.weight(1f))
            if (index != count) {
                NextCircleButton(
                    onClick = onNextClick,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = dimensions.paddingHuge)
                        .semantics { contentDescription = SurveyNextButton + index }
                )
            } else {
                PrimaryButton(
                    text = stringResource(id = R.string.survey_submit),
                    onClick = onSubmitClick,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.End)
                        .padding(bottom = dimensions.paddingHuge)
                )
            }
        }
    }
}

@Preview
@Composable
fun SurveyQuestionPreview() {
    ComposeTheme {
        SurveyQuestion(
            index = 1,
            count = 5,
            question = QuestionUiModel(
                id = "1",
                text = "How fulfilled did you feel during this WFH period?",
                coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_l",
                answers = null
            ),
            onCloseClick = {},
            onNextClick = {},
            onSubmitClick = {}
        )
    }
}

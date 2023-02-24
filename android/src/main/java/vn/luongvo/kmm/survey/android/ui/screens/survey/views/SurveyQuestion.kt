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
import timber.log.Timber
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.common.*
import vn.luongvo.kmm.survey.android.ui.preview.SurveyDetailParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.survey.*
import vn.luongvo.kmm.survey.android.ui.theme.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.domain.model.*

@Composable
fun SurveyQuestion(
    index: Int,
    count: Int,
    question: QuestionUiModel,
    onCloseClick: () -> Unit,
    onAnswer: (QuestionSubmission) -> Unit,
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

        SurveyQuestionContent(
            index,
            count,
            question,
            onAnswer,
            onNextClick,
            onSubmitClick
        )
    }
}

@Composable
private fun SurveyQuestionContent(
    index: Int,
    count: Int,
    question: QuestionUiModel,
    onAnswer: (QuestionSubmission) -> Unit,
    onNextClick: () -> Unit,
    onSubmitClick: () -> Unit
) {
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensions.paddingSmall)
                .weight(1f)
        ) {
            AnswerForm(
                question = question,
                onAnswer = onAnswer,
                modifier = Modifier.align(Alignment.Center)
            )
        }
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

@Composable
private fun AnswerForm(
    modifier: Modifier = Modifier,
    question: QuestionUiModel,
    onAnswer: (QuestionSubmission) -> Unit,
) {
    with(question) {
        when (displayType) {
            DisplayType.STAR -> StarRatingBar(
                answers = answers,
                onValueChange = { onAnswer(question.toQuestionSubmission(it)) },
                modifier = modifier
            )
            DisplayType.HEART -> HeartRatingBar(
                answers = answers,
                onValueChange = { onAnswer(question.toQuestionSubmission(it)) },
                modifier = modifier
            )
            DisplayType.SMILEY -> SmileyRatingBar(
                answers = answers,
                onValueChange = { onAnswer(question.toQuestionSubmission(it)) },
                modifier = modifier
            )
            DisplayType.CHOICE -> MultipleChoices(
                answers = answers,
                onValueChange = { onAnswer(question.toQuestionSubmission(it)) },
                modifier = modifier
            )
            DisplayType.NPS -> Nps(
                answers = answers,
                onValueChange = {
                    Timber.d("$displayType -> onValueChange: $it")
                },
                modifier = modifier
            )
            DisplayType.TEXTFIELD -> TextFields(
                answers = answers,
                onValueChange = { onAnswer(question.toQuestionSubmission(it)) },
                modifier = modifier
            )
            DisplayType.TEXTAREA -> TextArea(
                answer = answers.first(),
                onValueChange = { onAnswer(question.toQuestionSubmission(it)) },
                modifier = modifier
            )
            DisplayType.DROPDOWN -> Picker(
                answers = answers,
                onValueChange = {
                    Timber.d("$displayType -> onValueChange: $it")
                },
                modifier = modifier
            )
            else -> Unit
        }
    }
}

private fun QuestionUiModel.toQuestionSubmission(answer: AnswerSubmission) = toQuestionSubmission(listOf(answer))

private fun QuestionUiModel.toQuestionSubmission(answers: List<AnswerSubmission>) = QuestionSubmission(
    this.id,
    answers.toMutableList()
)

@Preview
@Composable
fun SurveyQuestionPreview(
    @PreviewParameter(SurveyDetailParameterProvider::class) params: SurveyDetailParameterProvider.Params
) {
    ComposeTheme {
        SurveyQuestion(
            index = 1,
            count = 5,
            question = params.survey.questions[0],
            onCloseClick = {},
            onAnswer = {},
            onNextClick = {},
            onSubmitClick = {}
        )
    }
}

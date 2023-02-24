package vn.luongvo.kmm.survey.android.ui.screens.survey.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import vn.luongvo.kmm.survey.android.ui.common.PrimaryTextField
import vn.luongvo.kmm.survey.android.ui.preview.SurveyDetailParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.survey.AnswerUiModel
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.domain.model.AnswerSubmission

@Composable
fun TextFields(
    answers: List<AnswerUiModel>,
    onValueChange: (List<AnswerSubmission>) -> Unit,
    modifier: Modifier = Modifier
) {
    var values by remember { mutableStateOf(answers.map { AnswerSubmission(id = it.id) }) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensions.paddingSmall),
        modifier = modifier
    ) {
        items(answers.size) { index ->
            val answer = answers[index]
            var value by remember {
                mutableStateOf(values.elementAtOrNull(index)?.answer.orEmpty())
            }

            PrimaryTextField(
                value = value,
                onValueChange = {
                    value = it
                    values = values.toMutableList().apply {
                        this[index] = AnswerSubmission(
                            id = answer.id,
                            answer = it
                        )
                    }
                    onValueChange(values)
                },
                placeholder = answer.inputMaskPlaceholder,
                imeAction = if (index == answers.lastIndex) ImeAction.Done else ImeAction.Next,
                isHighlightBackgroundIfNotEmpty = true
            )
        }
    }
}

@Preview
@Composable
fun TextFieldsPreview(
    @PreviewParameter(SurveyDetailParameterProvider::class) params: SurveyDetailParameterProvider.Params
) {
    TextFields(
        answers = params.survey.questions[0].answers,
        onValueChange = {}
    )
}

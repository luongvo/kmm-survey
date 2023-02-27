package vn.luongvo.kmm.survey.android.ui.screens.survey.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import vn.luongvo.kmm.survey.android.ui.common.PrimaryTextField
import vn.luongvo.kmm.survey.android.ui.preview.SurveyDetailParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.survey.AnswerInput
import vn.luongvo.kmm.survey.android.ui.screens.survey.AnswerUiModel
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions

@Composable
fun TextFields(
    answers: List<AnswerUiModel>,
    onValueChange: (List<AnswerInput>) -> Unit,
    modifier: Modifier = Modifier
) {
    var values by remember { mutableStateOf(answers.map { AnswerInput(it.id, "") }) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensions.paddingSmall),
        modifier = modifier
    ) {
        items(answers.size) { index ->
            val answer = answers[index]
            var value by remember {
                mutableStateOf(values.elementAtOrNull(index)?.content.orEmpty())
            }

            PrimaryTextField(
                value = value,
                onValueChange = {
                    value = it
                    values = values.toMutableList().apply {
                        this[index] = AnswerInput(answer.id, it)
                    }
                    onValueChange(values)
                },
                placeholder = answer.inputMaskPlaceholder.orEmpty(),
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

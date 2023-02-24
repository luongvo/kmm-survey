package vn.luongvo.kmm.survey.android.ui.screens.survey.views

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import vn.luongvo.kmm.survey.android.ui.common.PrimaryTextField
import vn.luongvo.kmm.survey.android.ui.preview.SurveyDetailParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.survey.AnswerUiModel
import vn.luongvo.kmm.survey.domain.model.AnswerSubmission

@Composable
fun TextArea(
    answer: AnswerUiModel,
    onValueChange: (AnswerSubmission) -> Unit,
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf(AnswerSubmission(answer.id, "")) }

    PrimaryTextField(
        value = value.answer.orEmpty(),
        onValueChange = {
            value = AnswerSubmission(answer.id, it)
            onValueChange(value)
        },
        placeholder = answer.inputMaskPlaceholder,
        singleLine = false,
        modifier = modifier
    )
}

@Preview
@Composable
fun TextAreaPreview(
    @PreviewParameter(SurveyDetailParameterProvider::class) params: SurveyDetailParameterProvider.Params
) {
    TextArea(
        answer = params.survey.questions[0].answers[0],
        onValueChange = {}
    )
}

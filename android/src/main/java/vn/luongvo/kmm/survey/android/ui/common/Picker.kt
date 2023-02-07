package vn.luongvo.kmm.survey.android.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.ListItemPicker
import vn.luongvo.kmm.survey.android.ui.preview.SurveyDetailParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.survey.AnswerUiModel
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography
import vn.luongvo.kmm.survey.android.ui.theme.White50

@Composable
fun Picker(
    modifier: Modifier = Modifier,
    answers: List<AnswerUiModel>,
    onValueChange: (String) -> Unit,
) {
    val values = answers.map { it.text }
    var state by remember { mutableStateOf(values[0]) }
    ListItemPicker(
        label = { it },
        value = state,
        onValueChange = {
            state = it
            onValueChange(it)
        },
        list = values,
        dividersColor = White50,
        textStyle = typography.body1.copy(fontSize = 20.sp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.paddingHuge)
    )
}

@Preview
@Composable
fun PickerPreview(
    @PreviewParameter(SurveyDetailParameterProvider::class) params: SurveyDetailParameterProvider.Params
) {
    Picker(
        answers = params.survey.questions[0].answers,
        onValueChange = {},
    )
}

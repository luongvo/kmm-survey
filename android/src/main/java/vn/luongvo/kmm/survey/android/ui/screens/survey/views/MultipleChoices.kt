package vn.luongvo.kmm.survey.android.ui.screens.survey.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.preview.SurveyDetailParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.survey.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography

@Composable
fun MultipleChoices(
    answers: List<AnswerUiModel>,
    onValueChange: (List<String>) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedIds by remember { mutableStateOf(emptySet<String>()) }
    LazyColumn(modifier = modifier.padding(horizontal = 80.dp)) {
        items(answers.size) { index ->
            val answer = answers[index]
            val isSelected = selectedIds.contains(answer.id)

            ChoiceItem(
                answer = answer,
                isSelected = isSelected,
                onClick = {
                    selectedIds = if (selectedIds.contains(answer.id)) {
                        selectedIds.minus(answer.id)
                    } else {
                        selectedIds.plus(answer.id)
                    }
                    onValueChange(selectedIds.toList())
                }
            )

            if (index < answers.lastIndex) {
                Spacer(
                    modifier = Modifier
                        .height(0.5.dp)
                        .fillMaxWidth()
                        .background(White)
                )
            }
        }
    }
}

@Composable
private fun ChoiceItem(
    answer: AnswerUiModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Transparent
        ),
        elevation = null,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .height(dimensions.inputHeight)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
        ) {
            Text(
                text = answer.text,
                color = if (isSelected) White else White.copy(alpha = 0.5f),
                style = if (isSelected) typography.h6 else typography.h6.copy(fontWeight = FontWeight.Normal),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            val imageResId =
                if (isSelected) R.drawable.ic_checkbox_selected else R.drawable.ic_checkbox_unselected
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Preview
@Composable
fun MultipleChoicesPreview(
    @PreviewParameter(SurveyDetailParameterProvider::class) params: SurveyDetailParameterProvider.Params
) {
    MultipleChoices(
        answers = params.survey.questions[0].answers,
        onValueChange = {}
    )
}

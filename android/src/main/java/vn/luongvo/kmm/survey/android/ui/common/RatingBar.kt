package vn.luongvo.kmm.survey.android.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import vn.luongvo.kmm.survey.android.ui.preview.SurveyDetailParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.survey.AnswerUiModel
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography

private const val EMOJI_STAR = "⭐️️"
private const val EMOJI_HEART = "❤️"

private const val EMOJI_ALPHA_SELECTED = 1f
private const val EMOJI_ALPHA_UNSELECTED = 0.5f

@Composable
fun StarRatingBar(
    answers: List<AnswerUiModel>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val emojis = List(answers.size) { EMOJI_STAR }
    RatingBar(
        modifier = modifier,
        emojis = emojis,
        answers = answers,
        onValueChange = onValueChange
    )
}

@Composable
fun HeartRatingBar(
    answers: List<AnswerUiModel>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val emojis = List(answers.size) { EMOJI_HEART }
    RatingBar(
        modifier = modifier,
        emojis = emojis,
        answers = answers,
        onValueChange = onValueChange
    )
}

@Composable
private fun RatingBar(
    emojis: List<String>,
    answers: List<AnswerUiModel>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    LazyRow(
        modifier = modifier
    ) {
        items(emojis.size) { index ->
            val isSelected = index <= selectedIndex
            val alpha = if (isSelected) EMOJI_ALPHA_SELECTED else EMOJI_ALPHA_UNSELECTED
            Button(
                onClick = {
                    selectedIndex = index
                    onValueChange(answers[selectedIndex].text)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                elevation = null,
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.size(34.dp)
            ) {
                Text(
                    text = emojis[index],
                    style = typography.h5,
                    modifier = Modifier.alpha(alpha)
                )
            }
            if (index < emojis.lastIndex) {
                Spacer(modifier = Modifier.width(dimensions.paddingSmall))
            }
        }
    }
}

@Preview
@Composable
fun StarRatingBarPreview(
    @PreviewParameter(SurveyDetailParameterProvider::class) params: SurveyDetailParameterProvider.Params
) {
    StarRatingBar(
        answers = params.survey.questions[0].answers,
        onValueChange = {},
    )
}

@Preview
@Composable
fun HeartRatingBarPreview(
    @PreviewParameter(SurveyDetailParameterProvider::class) params: SurveyDetailParameterProvider.Params
) {
    HeartRatingBar(
        answers = params.survey.questions[0].answers,
        onValueChange = {},
    )
}

package vn.luongvo.kmm.survey.android.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.luongvo.kmm.survey.android.ui.screens.survey.AnswerUiModel
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography

const val EMOJI_STAR = "⭐️️"

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    answers: List<AnswerUiModel>,
    onValueChange: (String) -> Unit,
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val emojis = List(answers.size) { EMOJI_STAR }
    LazyRow(
        modifier = modifier
    ) {
        items(emojis.size) { index ->
            val isSelected = index <= selectedIndex
            val alpha = if (isSelected) 1f else 0.5f
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
            if (index < emojis.size - 1) {
                Spacer(modifier = Modifier.width(dimensions.paddingSmall))
            }
        }
    }
}

@Preview
@Composable
fun RatingBarPreview() {
    RatingBar(
        answers = List(5) {
            AnswerUiModel(
                id = it.toString(),
                text = (it + 1).toString()
            )
        },
        onValueChange = {},
    )
}

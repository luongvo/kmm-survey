package vn.luongvo.kmm.survey.android.ui.screens.survey.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.luongvo.kmm.survey.android.ui.screens.survey.AnswerUiModel
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.shapes
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography
import vn.luongvo.kmm.survey.android.ui.theme.White50

@Composable
fun Nps(
    answers: List<AnswerUiModel>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    LazyRow(
        modifier = modifier
            .border(BorderStroke(0.5.dp, White), shapes.medium)
            .height(56.dp)
    ) {
        items(answers.size) { index ->
            val isSelected = index <= selectedIndex
            Button(
                onClick = {
                    selectedIndex = index
                    onValueChange(answers.getOrNull(selectedIndex)?.text.orEmpty())
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                elevation = null,
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .width(34.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = "${index + 1}",
                    color = if (isSelected) White else White50,
                    style = if (isSelected) typography.h6 else typography.h6.copy(fontWeight = FontWeight.Normal)
                )
            }
            if (index < answers.lastIndex) {
                Spacer(
                    modifier = Modifier
                        .width(0.5.dp)
                        .fillMaxHeight()
                        .background(White)
                )
            }
        }
    }
}

@Preview
@Composable
fun NpsPreview() {
    Nps(
        answers = List(10) {
            AnswerUiModel(
                id = it.toString(),
                text = (it + 1).toString()
            )
        },
        onValueChange = {}
    )
}

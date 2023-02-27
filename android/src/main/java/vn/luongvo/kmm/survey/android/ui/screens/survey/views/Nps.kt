package vn.luongvo.kmm.survey.android.ui.screens.survey.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.screens.survey.AnswerUiModel
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.shapes
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography
import vn.luongvo.kmm.survey.android.ui.theme.White50
import vn.luongvo.kmm.survey.domain.model.AnswerSubmission

@Composable
fun Nps(
    answers: List<AnswerUiModel>,
    onValueChange: (AnswerSubmission) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedIndex by remember { mutableStateOf(0) }
    ConstraintLayout(
        modifier = modifier
    ) {
        val (
            itemsRef,
            descriptionsRef,
        ) = createRefs()

        LazyRow(
            modifier = Modifier
                .border(BorderStroke(0.5.dp, White), shapes.medium)
                .height(dimensions.inputHeight)
                .wrapContentWidth()
                .constrainAs(itemsRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            items(answers.size) { index ->
                val isSelected = index <= selectedIndex
                val answer = answers[index]
                NpsItem(
                    answer = answer,
                    isSelected = isSelected,
                    onClick = {
                        selectedIndex = index
                        onValueChange(
                            AnswerSubmission(
                                id = answer.id,
                                answer = answer.text
                            )
                        )
                    }
                )

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

        NpsDescriptions(
            isHalfLeftItemsSelected = selectedIndex <= answers.size / 2,
            modifier = Modifier.constrainAs(descriptionsRef) {
                top.linkTo(itemsRef.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
    }
}

@Composable
private fun NpsItem(
    answer: AnswerUiModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
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
            text = answer.text,
            color = if (isSelected) White else White50,
            style = if (isSelected) typography.h6 else typography.h6.copy(fontWeight = FontWeight.Normal)
        )
    }
}

@Composable
private fun NpsDescriptions(
    isHalfLeftItemsSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(top = dimensions.paddingSmall)
    ) {
        Text(
            text = stringResource(id = R.string.nps_not_at_all_likely),
            color = if (isHalfLeftItemsSelected) White else White50,
            style = typography.body1.copy(fontWeight = FontWeight.Bold),
        )
        Text(
            text = stringResource(id = R.string.nps_extremely_likely),
            color = if (isHalfLeftItemsSelected.not()) White else White50,
            style = typography.body1.copy(fontWeight = FontWeight.Bold),
        )
    }
}

@Suppress("MagicNumber")
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

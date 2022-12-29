package vn.luongvo.kmm.survey.android.ui.screens.survey

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

@Composable
fun SurveyScreen(
    surveyId: String
) {
    SurveyScreenContent(surveyId)
}

@Composable
private fun SurveyScreenContent(surveyId: String) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Survey ID: $surveyId",
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SurveyScreenPreview() {
    ComposeTheme {
        SurveyScreenContent("surveyId")
    }
}

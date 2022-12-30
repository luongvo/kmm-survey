package vn.luongvo.kmm.survey.android.ui.screens.survey

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import vn.luongvo.kmm.survey.android.ui.common.DimmedImageBackground
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

@Composable
fun SurveyScreen(
    surveyId: String
) {
    SurveyScreenContent(surveyId)
}

@Composable
private fun SurveyScreenContent(surveyId: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        DimmedImageBackground(
            imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_"
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

package vn.luongvo.kmm.survey.android.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.home.SurveyUiModel
import vn.luongvo.kmm.survey.android.ui.screens.survey.*

class SurveyDetailParameterProvider : PreviewParameterProvider<SurveyDetailParameterProvider.Params> {

    private val surveyUiModel = SurveyUiModel(
        id = "1",
        title = "Scarlett Bangkok",
        description = "We'd love to hear from you!",
        coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
        questions = listOf(
            QuestionUiModel(
                id = "1",
                text = "How fulfilled did you feel during this WFH period?",
                displayType = DisplayType.STAR,
                coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_l",
                answers = List(5) {
                    AnswerUiModel(
                        id = it.toString(),
                        text = (it + 1).toString()
                    )
                }
            )
        )
    )

    override val values = sequenceOf(
        Params(isLoading = false),
        Params(isLoading = true)
    )

    inner class Params(
        val isLoading: Boolean,
        val survey: SurveyUiModel = surveyUiModel
    )
}

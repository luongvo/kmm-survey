package vn.luongvo.kmm.survey.android.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.home.SurveyUiModel
import vn.luongvo.kmm.survey.android.ui.screens.survey.*

@Suppress("MagicNumber")
class SurveyDetailParameterProvider : PreviewParameterProvider<SurveyDetailParameterProvider.Params> {

    override val values = sequenceOf(
        Params(isLoading = false)
    )

    class Params(
        val isLoading: Boolean,
        val survey: SurveyUiModel = SurveyUiModel(
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
                            text = (it + 1).toString(),
                            inputMaskPlaceholder = "Placeholder ${(it + 1)}"
                        )
                    }
                )
            )
        )
    )
}

class SurveyDetailScreenParameterProvider : PreviewParameterProvider<SurveyDetailParameterProvider.Params> {

    override val values = sequenceOf(
        SurveyDetailParameterProvider.Params(isLoading = false),
        SurveyDetailParameterProvider.Params(isLoading = true)
    )
}

package vn.luongvo.kmm.survey.android.ui.screens.survey

import vn.luongvo.kmm.survey.domain.model.Question

data class QuestionUiModel(
    val text: String,
    val coverImageUrl: String
)

fun Question.toUiModel() = QuestionUiModel(
    text = text.orEmpty(),
    coverImageUrl = coverImageUrl.orEmpty() + "l"
)

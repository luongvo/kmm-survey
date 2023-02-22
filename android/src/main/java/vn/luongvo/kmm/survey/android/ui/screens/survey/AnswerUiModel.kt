package vn.luongvo.kmm.survey.android.ui.screens.survey

import vn.luongvo.kmm.survey.domain.model.Answer

data class AnswerUiModel(
    val id: String,
    val text: String,
    val inputMaskPlaceholder: String
)

fun Answer.toUiModel() = AnswerUiModel(
    id = id,
    text = text.orEmpty(),
    inputMaskPlaceholder = inputMaskPlaceholder.orEmpty()
)

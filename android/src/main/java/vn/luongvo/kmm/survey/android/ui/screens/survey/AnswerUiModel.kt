package vn.luongvo.kmm.survey.android.ui.screens.survey

import vn.luongvo.kmm.survey.domain.model.Answer

data class AnswerUiModel(
    val id: String,
    val text: String
)

fun Answer.toUiModel() = AnswerUiModel(
    id = id,
    text = text.orEmpty()
)

fun List<AnswerUiModel>.textAt(index: Int) = getOrNull(index)?.text.orEmpty()

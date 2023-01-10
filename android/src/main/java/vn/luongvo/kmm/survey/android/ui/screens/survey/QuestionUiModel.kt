package vn.luongvo.kmm.survey.android.ui.screens.survey

import vn.luongvo.kmm.survey.domain.model.Answer
import vn.luongvo.kmm.survey.domain.model.Question

data class QuestionUiModel(
    val id: String,
    val text: String,
    val coverImageUrl: String,
    // TODO integrate ui model in https://github.com/luongvo/kmm-survey/issues/34
    val answers: List<Answer>?
)

fun Question.toUiModel() = QuestionUiModel(
    id = id,
    text = text.orEmpty(),
    coverImageUrl = coverImageUrl.orEmpty() + "l",
    answers = answers
)

package vn.luongvo.kmm.survey.android.ui.screens.home

import vn.luongvo.kmm.survey.android.ui.screens.survey.QuestionUiModel
import vn.luongvo.kmm.survey.android.ui.screens.survey.toUiModel
import vn.luongvo.kmm.survey.domain.model.Survey

data class SurveyUiModel(
    val id: String,
    val title: String,
    val description: String,
    val coverImageUrl: String,
    val questions: List<QuestionUiModel> = emptyList()
)

fun Survey.toUiModel() = SurveyUiModel(
    id = id,
    title = title,
    description = description,
    coverImageUrl = coverImageUrl + "l",
    questions = questions?.map { it.toUiModel() }.orEmpty()
)

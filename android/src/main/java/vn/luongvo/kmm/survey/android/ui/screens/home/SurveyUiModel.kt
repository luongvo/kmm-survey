package vn.luongvo.kmm.survey.android.ui.screens.home

import vn.luongvo.kmm.survey.domain.model.Survey

data class SurveyUiModel(
    val title: String,
    val description: String,
    val coverImageUrl: String
)

fun Survey.toUiModel() = SurveyUiModel(
    title = title,
    description = description,
    coverImageUrl = coverImageUrl + "l"
)

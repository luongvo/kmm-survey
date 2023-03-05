package vn.luongvo.kmm.survey.domain.model

import vn.luongvo.kmm.survey.data.local.model.SurveyRealmObject

data class Survey(
    val id: String,
    val title: String,
    val description: String,
    val coverImageUrl: String,
    val questions: List<Question>?
)

fun Survey.toSurveyRealmObject() = SurveyRealmObject(
    id = id,
    title = title,
    description = description,
    coverImageUrl = coverImageUrl
)

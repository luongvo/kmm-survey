package vn.luongvo.kmm.survey.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import vn.luongvo.kmm.survey.domain.model.Survey

@Serializable
data class SurveyResponse(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("cover_image_url")
    val coverImageUrl: String,
    @SerialName("questions")
    val questions: List<QuestionResponse>? = null
)

fun SurveyResponse.toSurvey() = Survey(
    id = id,
    title = title,
    description = description,
    coverImageUrl = coverImageUrl,
    questions = questions?.map { it.toQuestion() }
)

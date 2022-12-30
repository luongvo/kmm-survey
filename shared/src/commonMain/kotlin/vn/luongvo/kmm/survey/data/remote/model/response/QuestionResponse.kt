package vn.luongvo.kmm.survey.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import vn.luongvo.kmm.survey.domain.model.Question

@Serializable
data class QuestionResponse(
    @SerialName("id")
    val id: String,
    @SerialName("text")
    val text: String,
    @SerialName("display_order")
    val displayOrder: Int,
    @SerialName("display_type")
    val displayType: String,
    @SerialName("cover_image_url")
    val coverImageUrl: String,
    @SerialName("answers")
    val answers: List<AnswerResponse>
)

fun QuestionResponse.toQuestion(): Question = Question(
    id = id,
    text = text,
    displayOrder = displayOrder,
    displayType = displayType,
    coverImageUrl = coverImageUrl,
    answers = answers.map { it.toAnswer() }
)

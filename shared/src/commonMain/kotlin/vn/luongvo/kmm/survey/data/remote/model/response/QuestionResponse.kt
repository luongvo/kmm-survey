package vn.luongvo.kmm.survey.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import vn.luongvo.kmm.survey.domain.model.Question

@Serializable
data class QuestionResponse(
    @SerialName("id")
    val id: String,
    @SerialName("text")
    val text: String? = null,
    @SerialName("display_order")
    val displayOrder: Int? = null,
    @SerialName("display_type")
    val displayType: String? = null,
    @SerialName("cover_image_url")
    val coverImageUrl: String? = null,
    @SerialName("answers")
    val answers: List<AnswerResponse>? = null
)

fun QuestionResponse.toQuestion(): Question = Question(
    id = id,
    text = text,
    displayOrder = displayOrder,
    displayType = displayType,
    coverImageUrl = coverImageUrl,
    answers = answers?.map { it.toAnswer() }
)

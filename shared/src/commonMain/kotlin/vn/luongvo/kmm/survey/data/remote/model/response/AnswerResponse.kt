package vn.luongvo.kmm.survey.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import vn.luongvo.kmm.survey.domain.model.Answer

@Serializable
data class AnswerResponse(
    @SerialName("id")
    val id: String,
    @SerialName("text")
    val text: String?,
    @SerialName("display_order")
    val displayOrder: Int,
    @SerialName("input_mask_placeholder")
    val inputMaskPlaceholder: String?
)

fun AnswerResponse.toAnswer(): Answer = Answer(
    id = id,
    text = text,
    displayOrder = displayOrder,
    inputMaskPlaceholder = inputMaskPlaceholder
)

package vn.luongvo.kmm.survey.domain.model

data class Answer(
    val id: String,
    val text: String?,
    val displayOrder: Int,
    var inputMaskPlaceholder: String?
)

package vn.luongvo.kmm.survey.domain.model

data class Question(
    val id: String,
    val text: String?,
    val displayOrder: Int?,
    val displayType: String?,
    val coverImageUrl: String?,
    val answers: List<Answer>?
)

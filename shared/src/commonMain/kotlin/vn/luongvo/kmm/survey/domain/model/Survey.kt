package vn.luongvo.kmm.survey.domain.model

data class Survey(
    val id: String,
    val title: String,
    val description: String,
    val coverImageUrl: String,
    val questions: List<Question>?
)

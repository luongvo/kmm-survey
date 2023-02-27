package vn.luongvo.kmm.survey.domain.model

data class SurveySubmission(
    val id: String,
    val questions: List<QuestionSubmission>
)

data class QuestionSubmission(
    val id: String,
    val answers: MutableList<AnswerSubmission>
)

data class AnswerSubmission(
    val id: String,
    val answer: String? = null
)

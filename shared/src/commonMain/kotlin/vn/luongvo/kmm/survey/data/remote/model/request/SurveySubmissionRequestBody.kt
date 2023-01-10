package vn.luongvo.kmm.survey.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import vn.luongvo.kmm.survey.domain.model.*

@Serializable
data class SurveySubmissionRequestBody(
    @SerialName("survey_id")
    val surveyId: String,
    @SerialName("questions")
    val questions: List<QuestionSubmissionRequestBody>
) {

    companion object {
        fun build(surveySubmission: SurveySubmission) = SurveySubmissionRequestBody(
            surveySubmission.id,
            surveySubmission.questions.map(QuestionSubmissionRequestBody::build)
        )
    }
}

@Serializable
data class QuestionSubmissionRequestBody(
    @SerialName("id")
    val id: String,
    @SerialName("answers")
    val answers: List<AnswerSubmissionRequestBody>
) {

    companion object {
        fun build(questionSubmission: QuestionSubmission) = QuestionSubmissionRequestBody(
            questionSubmission.id,
            questionSubmission.answers.map(AnswerSubmissionRequestBody::build)
        )
    }
}

@Serializable
data class AnswerSubmissionRequestBody(
    @SerialName("id")
    val id: String,
    @SerialName("answer")
    val answer: String?
) {

    companion object {
        fun build(answerSubmission: AnswerSubmission) = AnswerSubmissionRequestBody(
            answerSubmission.id,
            answerSubmission.answer
        )
    }
}

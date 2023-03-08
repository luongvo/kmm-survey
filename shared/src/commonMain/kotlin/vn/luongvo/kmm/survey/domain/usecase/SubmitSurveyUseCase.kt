package vn.luongvo.kmm.survey.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.domain.model.SurveySubmission
import vn.luongvo.kmm.survey.domain.repository.SurveyRepository

interface SubmitSurveyUseCase {
    operator fun invoke(submission: SurveySubmission): Flow<Unit>
}

class SubmitSurveyUseCaseImpl(private val repository: SurveyRepository) : SubmitSurveyUseCase {

    override operator fun invoke(submission: SurveySubmission): Flow<Unit> {
        return repository.submitSurvey(submission)
    }
}

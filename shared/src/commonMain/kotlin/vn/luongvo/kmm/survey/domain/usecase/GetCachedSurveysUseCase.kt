package vn.luongvo.kmm.survey.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.domain.model.Survey
import vn.luongvo.kmm.survey.domain.repository.SurveyRepository

interface GetCachedSurveysUseCase {
    operator fun invoke(): Flow<List<Survey>>
}

class GetCachedSurveysUseCaseImpl(private val repository: SurveyRepository) : GetCachedSurveysUseCase {

    override operator fun invoke(): Flow<List<Survey>> {
        return repository.getCachedSurveys()
    }
}

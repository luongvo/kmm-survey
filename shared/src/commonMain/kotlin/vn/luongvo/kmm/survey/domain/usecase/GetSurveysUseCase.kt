package vn.luongvo.kmm.survey.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.domain.model.Survey
import vn.luongvo.kmm.survey.domain.repository.SurveyRepository

interface GetSurveysUseCase {

    operator fun invoke(pageNumber: Int, pageSize: Int): Flow<List<Survey>>
}

class GetSurveysUseCaseImpl(private val repository: SurveyRepository) : GetSurveysUseCase {

    override operator fun invoke(pageNumber: Int, pageSize: Int): Flow<List<Survey>> {
        return repository.getSurveys(pageNumber = pageNumber, pageSize = pageSize)
    }
}

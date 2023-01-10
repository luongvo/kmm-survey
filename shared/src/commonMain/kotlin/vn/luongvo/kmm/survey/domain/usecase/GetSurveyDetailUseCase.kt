package vn.luongvo.kmm.survey.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.domain.model.Survey
import vn.luongvo.kmm.survey.domain.repository.SurveyRepository

interface GetSurveyDetailUseCase {

    operator fun invoke(id: String): Flow<Survey>
}

class GetSurveyDetailUseCaseImpl(private val repository: SurveyRepository) : GetSurveyDetailUseCase {

    override operator fun invoke(id: String): Flow<Survey> {
        return repository.getSurveyDetail(id = id)
    }
}

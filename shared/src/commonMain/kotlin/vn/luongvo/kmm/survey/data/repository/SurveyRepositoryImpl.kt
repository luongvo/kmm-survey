package vn.luongvo.kmm.survey.data.repository

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.data.extensions.flowTransform
import vn.luongvo.kmm.survey.data.remote.datasource.SurveyRemoteDataSource
import vn.luongvo.kmm.survey.data.remote.model.request.SurveySubmissionRequestBody
import vn.luongvo.kmm.survey.data.remote.model.response.toSurvey
import vn.luongvo.kmm.survey.domain.model.Survey
import vn.luongvo.kmm.survey.domain.model.SurveySubmission
import vn.luongvo.kmm.survey.domain.repository.SurveyRepository

class SurveyRepositoryImpl(
    private val surveyRemoteDataSource: SurveyRemoteDataSource
) : SurveyRepository {

    override fun getSurveys(pageNumber: Int, pageSize: Int): Flow<List<Survey>> = flowTransform {
        surveyRemoteDataSource
            .getSurveys(pageNumber = pageNumber, pageSize = pageSize)
            .map { it.toSurvey() }
    }

    override fun getSurveyDetail(id: String): Flow<Survey> = flowTransform {
        surveyRemoteDataSource
            .getSurveyDetail(id = id)
            .toSurvey()
    }

    override fun submitSurvey(submission: SurveySubmission): Flow<Unit> = flowTransform {
        surveyRemoteDataSource
            .submitSurvey(SurveySubmissionRequestBody.build(submission))
    }
}

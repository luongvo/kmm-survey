package vn.luongvo.kmm.survey.data.repository

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.data.extensions.flowTransform
import vn.luongvo.kmm.survey.data.local.datasource.SurveyLocalDataSource
import vn.luongvo.kmm.survey.data.local.model.toSurvey
import vn.luongvo.kmm.survey.data.remote.datasource.SurveyRemoteDataSource
import vn.luongvo.kmm.survey.data.remote.model.request.SurveySubmissionRequestBody
import vn.luongvo.kmm.survey.data.remote.model.response.toSurvey
import vn.luongvo.kmm.survey.domain.model.*
import vn.luongvo.kmm.survey.domain.repository.SurveyRepository

class SurveyRepositoryImpl(
    private val surveyRemoteDataSource: SurveyRemoteDataSource,
    private val surveyLocalDataSource: SurveyLocalDataSource
) : SurveyRepository {

    override fun getSurveys(pageNumber: Int, pageSize: Int, isRefresh: Boolean): Flow<List<Survey>> = flowTransform {
        val surveys = surveyRemoteDataSource
            .getSurveys(pageNumber = pageNumber, pageSize = pageSize)
            .map { it.toSurvey() }

        if (isRefresh) {
            surveyLocalDataSource.clear()
        }
        surveyLocalDataSource.saveSurveys(surveys.map { it.toSurveyRealmObject() })

        surveys
    }

    override fun getCachedSurveys(): Flow<List<Survey>> = flowTransform {
        surveyLocalDataSource
            .getSurveys()
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

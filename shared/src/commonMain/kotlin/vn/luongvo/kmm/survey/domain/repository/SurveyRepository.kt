package vn.luongvo.kmm.survey.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.domain.model.Survey
import vn.luongvo.kmm.survey.domain.model.SurveySubmission

interface SurveyRepository {
    fun getSurveys(pageNumber: Int, pageSize: Int, isRefresh: Boolean): Flow<List<Survey>>

    fun getCachedSurveys(): Flow<List<Survey>>

    fun getSurveyDetail(id: String): Flow<Survey>

    fun submitSurvey(submission: SurveySubmission): Flow<Unit>
}

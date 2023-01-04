package vn.luongvo.kmm.survey.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.domain.model.Survey

interface SurveyRepository {

    fun getSurveys(pageNumber: Int, pageSize: Int): Flow<List<Survey>>

    fun getSurveyDetail(id: String): Flow<Survey>
}

package vn.luongvo.kmm.survey.data.remote.datasource

import vn.luongvo.kmm.survey.data.remote.ApiClient
import vn.luongvo.kmm.survey.data.remote.model.request.SurveySubmissionRequestBody
import vn.luongvo.kmm.survey.data.remote.model.response.SurveyResponse

interface SurveyRemoteDataSource {

    suspend fun getSurveys(pageNumber: Int, pageSize: Int): List<SurveyResponse>

    suspend fun getSurveyDetail(id: String): SurveyResponse

    suspend fun submitSurvey(body: SurveySubmissionRequestBody)
}

class SurveyRemoteDataSourceImpl(private val apiClient: ApiClient) : SurveyRemoteDataSource {

    override suspend fun getSurveys(pageNumber: Int, pageSize: Int): List<SurveyResponse> {
        return apiClient.get(
            path = "/v1/surveys?page[number]=$pageNumber&page[size]=$pageSize"
        )
    }

    override suspend fun getSurveyDetail(id: String): SurveyResponse {
        return apiClient.get(
            path = "/v1/surveys/$id"
        )
    }

    override suspend fun submitSurvey(body: SurveySubmissionRequestBody) {
        return apiClient.post(
            path = "/v1/responses",
            requestBody = body
        )
    }
}

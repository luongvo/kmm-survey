package vn.luongvo.kmm.survey.data.remote.datasource

import vn.luongvo.kmm.survey.data.remote.ApiClient
import vn.luongvo.kmm.survey.data.remote.model.request.LoginRequestBody
import vn.luongvo.kmm.survey.data.remote.model.response.Response
import vn.luongvo.kmm.survey.data.remote.model.response.TokenResponse

interface AuthRemoteDataSource {

    suspend fun logIn(body: LoginRequestBody): Response<TokenResponse>
}

class AuthRemoteDataSourceImpl(private val apiClient: ApiClient) : AuthRemoteDataSource {

    override suspend fun logIn(body: LoginRequestBody): Response<TokenResponse> {
        return apiClient.post(
            path = "/v1/oauth/token",
            requestBody = body
        )
    }
}

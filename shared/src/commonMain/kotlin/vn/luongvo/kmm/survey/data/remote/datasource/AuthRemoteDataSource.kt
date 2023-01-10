package vn.luongvo.kmm.survey.data.remote.datasource

import vn.luongvo.kmm.survey.data.remote.ApiClient
import vn.luongvo.kmm.survey.data.remote.model.request.*
import vn.luongvo.kmm.survey.data.remote.model.response.TokenResponse

interface AuthRemoteDataSource {

    suspend fun logIn(body: LoginRequestBody): TokenResponse

    suspend fun refreshToken(body: RefreshTokenRequestBody): TokenResponse

    suspend fun logOut(body: LogoutRequestBody)
}

class AuthRemoteDataSourceImpl(private val apiClient: ApiClient) : AuthRemoteDataSource {

    override suspend fun logIn(body: LoginRequestBody): TokenResponse {
        return apiClient.post(
            path = "/v1/oauth/token",
            requestBody = body
        )
    }

    override suspend fun refreshToken(body: RefreshTokenRequestBody): TokenResponse {
        return apiClient.post(
            path = "/v1/oauth/token",
            requestBody = body
        )
    }

    override suspend fun logOut(body: LogoutRequestBody) {
        return apiClient.post(
            path = "/v1/oauth/revoke",
            requestBody = body
        )
    }
}

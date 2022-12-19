package vn.luongvo.kmm.survey.data.remote.datasource

import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.data.remote.apiclient.ApiClient
import vn.luongvo.kmm.survey.data.remote.apiclient.builder.path
import vn.luongvo.kmm.survey.data.remote.model.request.LoginRequestBody
import vn.luongvo.kmm.survey.data.remote.model.response.TokenResponse

interface AuthRemoteDataSource {

    fun logIn(body: LoginRequestBody): Flow<TokenResponse>
}

class AuthRemoteDataSourceImpl(private val apiClient: ApiClient) : AuthRemoteDataSource {

    override fun logIn(body: LoginRequestBody): Flow<TokenResponse> {
        return apiClient.body(
            HttpRequestBuilder().apply {
                path("/v1/oauth/token")
                method = HttpMethod.Post
                setBody(body)
            }
        )
    }
}

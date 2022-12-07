package vn.luongvo.kmm.survey.data.remote.datasource

import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.data.remote.apiclient.ApiClient
import vn.luongvo.kmm.survey.data.remote.apiclient.builder.path
import vn.luongvo.kmm.survey.data.remote.body.LoginRequestBody

interface AuthRemoteDataSource {
    fun logIn(body: LoginRequestBody): Flow<String>
}

class AuthRemoteDataSourceImpl(private val apiClient: ApiClient) : AuthRemoteDataSource {

    override fun logIn(body: LoginRequestBody): Flow<String> {
        return apiClient.body(
            HttpRequestBuilder().apply {
                path("/v1/oauth/token")
                method = HttpMethod.Post
                setBody(body)
            }
        )
    }
}

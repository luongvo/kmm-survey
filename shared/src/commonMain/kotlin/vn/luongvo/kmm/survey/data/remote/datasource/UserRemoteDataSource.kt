package vn.luongvo.kmm.survey.data.remote.datasource

import vn.luongvo.kmm.survey.data.remote.ApiClient
import vn.luongvo.kmm.survey.data.remote.model.response.UserResponse

interface UserRemoteDataSource {

    suspend fun getUserProfile(): UserResponse

    fun clearClientTokenConfig()
}

class UserRemoteDataSourceImpl(private val apiClient: ApiClient) : UserRemoteDataSource {

    override suspend fun getUserProfile(): UserResponse {
        return apiClient.get(
            path = "/v1/me"
        )
    }

    override fun clearClientTokenConfig() {
        apiClient.clearToken()
    }
}

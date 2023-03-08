package vn.luongvo.kmm.survey.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.domain.model.User

interface UserRepository {
    fun getUserProfile(): Flow<User>

    fun clearClientTokenConfig()
}

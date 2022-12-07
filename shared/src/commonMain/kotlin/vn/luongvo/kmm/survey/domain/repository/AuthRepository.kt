package vn.luongvo.kmm.survey.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun logIn(email: String, password: String): Flow<String>
}

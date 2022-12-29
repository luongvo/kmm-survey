package vn.luongvo.kmm.survey.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.domain.model.Token

interface AuthRepository {

    fun logIn(email: String, password: String): Flow<Token>

    fun refreshToken(refreshToken: String): Flow<Token>

    fun saveToken(token: Token)

    val isLoggedIn: Flow<Boolean>
}

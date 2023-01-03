package vn.luongvo.kmm.survey.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import vn.luongvo.kmm.survey.data.extensions.flowTransform
import vn.luongvo.kmm.survey.data.local.datasource.TokenLocalDataSource
import vn.luongvo.kmm.survey.data.remote.datasource.AuthRemoteDataSource
import vn.luongvo.kmm.survey.data.remote.model.request.*
import vn.luongvo.kmm.survey.data.remote.model.response.toToken
import vn.luongvo.kmm.survey.domain.model.Token
import vn.luongvo.kmm.survey.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val tokenLocalDataSource: TokenLocalDataSource
) : AuthRepository {

    override fun logIn(email: String, password: String): Flow<Token> = flowTransform {
        authRemoteDataSource
            .logIn(LoginRequestBody(email = email, password = password))
            .toToken()
    }

    override fun refreshToken(refreshToken: String): Flow<Token> = flowTransform {
        authRemoteDataSource
            .refreshToken(RefreshTokenRequestBody(refreshToken = refreshToken))
            .toToken()
    }

    override fun saveToken(token: Token) {
        tokenLocalDataSource.saveToken(token)
    }

    override fun clearToken() {
        tokenLocalDataSource.clear()
    }

    override val isLoggedIn: Flow<Boolean>
        get() = flowOf(
            tokenLocalDataSource.tokenType.isNotBlank() && tokenLocalDataSource.accessToken.isNotBlank()
        )

    override fun logOut(): Flow<Unit> = flowTransform {
        authRemoteDataSource
            .logOut(LogoutRequestBody(accessToken = tokenLocalDataSource.accessToken))
    }
}

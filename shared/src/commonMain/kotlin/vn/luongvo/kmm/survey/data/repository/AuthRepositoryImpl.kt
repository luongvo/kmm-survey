package vn.luongvo.kmm.survey.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import vn.luongvo.kmm.survey.data.extensions.flowTransform
import vn.luongvo.kmm.survey.data.local.datasource.TokenLocalDataSource
import vn.luongvo.kmm.survey.data.remote.datasource.AuthRemoteDataSource
import vn.luongvo.kmm.survey.data.remote.model.request.LoginRequestBody
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

    override fun saveToken(token: Token) {
        tokenLocalDataSource.saveToken(token)
    }

    override val isLoggedIn: Flow<Boolean>
        get() = flowOf(
            tokenLocalDataSource.tokenType.isNotBlank() && tokenLocalDataSource.accessToken.isNotBlank()
        )
}

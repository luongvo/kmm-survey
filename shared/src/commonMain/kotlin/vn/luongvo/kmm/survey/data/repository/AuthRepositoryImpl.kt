package vn.luongvo.kmm.survey.data.repository

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.data.extensions.flowTransform
import vn.luongvo.kmm.survey.data.remote.datasource.AuthRemoteDataSource
import vn.luongvo.kmm.survey.data.remote.model.request.LoginRequestBody
import vn.luongvo.kmm.survey.data.remote.model.response.toToken
import vn.luongvo.kmm.survey.domain.model.Token
import vn.luongvo.kmm.survey.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override fun logIn(email: String, password: String): Flow<Token> = flowTransform {
        authRemoteDataSource
            .logIn(LoginRequestBody(email = email, password = password))
            .data.attributes.toToken()
    }
}

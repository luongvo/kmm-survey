package vn.luongvo.kmm.survey.data.repository

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.data.remote.body.LoginRequestBody
import vn.luongvo.kmm.survey.data.remote.datasource.AuthRemoteDataSource
import vn.luongvo.kmm.survey.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override fun logIn(email: String, password: String): Flow<String> {
        return authRemoteDataSource
            .logIn(LoginRequestBody(email = email, password = password))
    }
}

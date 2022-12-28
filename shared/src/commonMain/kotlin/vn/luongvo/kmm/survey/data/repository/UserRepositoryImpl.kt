package vn.luongvo.kmm.survey.data.repository

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.data.extensions.flowTransform
import vn.luongvo.kmm.survey.data.remote.datasource.UserRemoteDataSource
import vn.luongvo.kmm.survey.data.remote.model.response.toUser
import vn.luongvo.kmm.survey.domain.model.User
import vn.luongvo.kmm.survey.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override fun getUserProfile(): Flow<User> = flowTransform {
        userRemoteDataSource
            .getUserProfile()
            .toUser()
    }
}

package vn.luongvo.kmm.survey.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.domain.model.User
import vn.luongvo.kmm.survey.domain.repository.UserRepository

interface GetUserProfileUseCase {
    operator fun invoke(): Flow<User>
}

class GetUserProfileUseCaseImpl(private val repository: UserRepository) : GetUserProfileUseCase {

    override operator fun invoke(): Flow<User> {
        return repository.getUserProfile()
    }
}

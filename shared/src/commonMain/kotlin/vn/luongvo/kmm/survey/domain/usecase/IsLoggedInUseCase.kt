package vn.luongvo.kmm.survey.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.domain.repository.AuthRepository

interface IsLoggedInUseCase {
    operator fun invoke(): Flow<Boolean>
}

class IsLoggedInUseCaseImpl(private val repository: AuthRepository) : IsLoggedInUseCase {

    override operator fun invoke(): Flow<Boolean> {
        return repository.isLoggedIn
    }
}

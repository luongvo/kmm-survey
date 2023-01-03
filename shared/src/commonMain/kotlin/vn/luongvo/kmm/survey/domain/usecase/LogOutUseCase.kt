package vn.luongvo.kmm.survey.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import vn.luongvo.kmm.survey.domain.repository.AuthRepository

interface LogOutUseCase {

    operator fun invoke(): Flow<Unit>
}

class LogOutUseCaseImpl(private val repository: AuthRepository) : LogOutUseCase {

    override operator fun invoke(): Flow<Unit> {
        return repository.logOut()
            .onEach { repository.clearToken() }
    }
}

package vn.luongvo.kmm.survey.domain.usecase

import kotlinx.coroutines.flow.*
import vn.luongvo.kmm.survey.domain.repository.AuthRepository
import vn.luongvo.kmm.survey.domain.repository.UserRepository

interface LogOutUseCase {

    operator fun invoke(): Flow<Unit>
}

class LogOutUseCaseImpl(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : LogOutUseCase {

    override operator fun invoke(): Flow<Unit> {
        return authRepository.logOut()
            .catch {
                // On error resume next
                emit(Unit)
            }
            .onEach {
                authRepository.clearLocalToken()
                userRepository.clearClientTokenConfig()
            }
    }
}

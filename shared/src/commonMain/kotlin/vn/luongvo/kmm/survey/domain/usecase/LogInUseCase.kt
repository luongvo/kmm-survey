package vn.luongvo.kmm.survey.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.luongvo.kmm.survey.domain.repository.AuthRepository

interface LogInUseCase {
    operator fun invoke(email: String, password: String): Flow<String>
}

class LogInUseCaseImpl(private val repository: AuthRepository) : LogInUseCase {

    override operator fun invoke(email: String, password: String): Flow<String> {
        return repository.logIn(email, password)
    }
}

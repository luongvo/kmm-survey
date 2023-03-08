package vn.luongvo.kmm.survey.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import vn.luongvo.kmm.survey.domain.model.Token
import vn.luongvo.kmm.survey.domain.repository.AuthRepository

interface RefreshTokenUseCase {
    operator fun invoke(refreshToken: String): Flow<Token>
}

class RefreshTokenUseCaseImpl(private val repository: AuthRepository) : RefreshTokenUseCase {

    override operator fun invoke(refreshToken: String): Flow<Token> {
        return repository.refreshToken(refreshToken)
            .onEach { token -> repository.saveToken(token) }
    }
}

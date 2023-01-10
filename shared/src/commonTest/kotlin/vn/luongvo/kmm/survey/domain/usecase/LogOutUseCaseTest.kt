package vn.luongvo.kmm.survey.domain.usecase

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.domain.repository.AuthRepository
import vn.luongvo.kmm.survey.domain.repository.UserRepository
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class LogOutUseCaseTest {

    @Mock
    private val mockAuthRepository = mock(AuthRepository::class)
    private val mockUserRepository = mock(UserRepository::class)

    private lateinit var useCase: LogOutUseCase

    @BeforeTest
    fun setUp() {
        useCase = LogOutUseCaseImpl(mockAuthRepository, mockUserRepository)
    }

    @Test
    fun `when calling logOut successfully - it returns Unit`() = runTest {
        given(mockAuthRepository)
            .function(mockAuthRepository::logOut)
            .whenInvoked()
            .thenReturn(flowOf(Unit))

        useCase().test {
            awaitItem() shouldBe Unit
            awaitComplete()
        }

        verify(mockAuthRepository)
            .function(mockAuthRepository::clearLocalToken)
            .wasInvoked(exactly = 1.time)
        verify(mockUserRepository)
            .function(mockUserRepository::clearClientTokenConfig)
            .wasInvoked(exactly = 1.time)
    }

    @Test
    fun `when calling logOut fails - it still returns Unit`() = runTest {
        val throwable = Throwable()
        given(mockAuthRepository)
            .function(mockAuthRepository::logOut)
            .whenInvoked()
            .thenReturn(
                flow { throw throwable }
            )

        useCase().test {
            awaitItem() shouldBe Unit
            awaitComplete()
        }

        verify(mockAuthRepository)
            .function(mockAuthRepository::clearLocalToken)
            .wasInvoked(exactly = 1.time)
        verify(mockUserRepository)
            .function(mockUserRepository::clearClientTokenConfig)
            .wasInvoked(exactly = 1.time)
    }
}

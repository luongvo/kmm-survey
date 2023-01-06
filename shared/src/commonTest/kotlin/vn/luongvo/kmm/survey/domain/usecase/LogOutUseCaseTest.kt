package vn.luongvo.kmm.survey.domain.usecase

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.domain.repository.AuthRepository
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class LogOutUseCaseTest {

    @Mock
    private val mockRepository = mock(AuthRepository::class)

    private lateinit var useCase: LogOutUseCase

    @BeforeTest
    fun setUp() {
        useCase = LogOutUseCaseImpl(mockRepository)
    }

    @Test
    fun `when calling logOut successfully - it returns Unit`() = runTest {
        given(mockRepository)
            .function(mockRepository::logOut)
            .whenInvoked()
            .thenReturn(flowOf(Unit))

        useCase().test {
            awaitItem() shouldBe Unit
            awaitComplete()
        }

        verify(mockRepository)
            .function(mockRepository::clearToken)
            .wasInvoked(exactly = 1.time)
    }

    @Test
    fun `when calling logOut fails - it throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockRepository)
            .function(mockRepository::logOut)
            .whenInvoked()
            .thenReturn(
                flow { throw throwable }
            )

        useCase().test {
            awaitError() shouldBe throwable
        }

        verify(mockRepository)
            .function(mockRepository::clearToken)
            .wasNotInvoked()
    }
}

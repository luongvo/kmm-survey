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
class IsLoggedInUseCaseTest {

    @Mock
    private val mockRepository = mock(AuthRepository::class)

    private lateinit var useCase: IsLoggedInUseCase

    @BeforeTest
    fun setUp() {
        useCase = IsLoggedInUseCaseImpl(mockRepository)
    }

    @Test
    fun `when calling isLoggedIn successfully - it returns logged in status`() = runTest {
        given(mockRepository)
            .invocation { mockRepository.isLoggedIn }
            .thenReturn(flowOf(true))

        useCase().test {
            awaitItem() shouldBe true
            awaitComplete()
        }
    }

    @Test
    fun `when calling isLoggedIn fails - it throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockRepository)
            .invocation { mockRepository.isLoggedIn }
            .thenReturn(
                flow { throw throwable }
            )

        useCase().test {
            awaitError() shouldBe throwable
        }
    }
}

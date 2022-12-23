package vn.luongvo.kmm.survey.domain.usecase

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.domain.repository.AuthRepository
import vn.luongvo.kmm.survey.test.token
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class LogInUseCaseTest {

    @Mock
    private val mockRepository = mock(AuthRepository::class)

    private lateinit var useCase: LogInUseCase

    @BeforeTest
    fun setUp() {
        useCase = LogInUseCaseImpl(mockRepository)
    }

    @Test
    fun `when calling logIn successfully - it returns token`() = runTest {
        given(mockRepository)
            .function(mockRepository::logIn)
            .whenInvokedWith(any(), any())
            .thenReturn(flowOf(token))

        useCase("email", "password").test {
            awaitItem() shouldBe token
            awaitComplete()
        }
    }

    @Test
    fun `when calling logIn fails - it throws error`() = runTest {
        val throwable = Throwable()
        given(mockRepository)
            .function(mockRepository::logIn)
            .whenInvokedWith(any(), any())
            .thenReturn(
                flow { throw throwable }
            )

        useCase("email", "password").test {
            awaitError() shouldBe throwable
        }
    }
}

package vn.luongvo.kmm.survey.domain.usecase

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.domain.repository.AuthRepository
import vn.luongvo.kmm.survey.test.Fake.token
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

        verify(mockRepository)
            .function(mockRepository::saveToken)
            .with(eq(token))
            .wasInvoked(exactly = 1.time)
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

        verify(mockRepository)
            .function(mockRepository::saveToken)
            .with(any())
            .wasNotInvoked()
    }
}

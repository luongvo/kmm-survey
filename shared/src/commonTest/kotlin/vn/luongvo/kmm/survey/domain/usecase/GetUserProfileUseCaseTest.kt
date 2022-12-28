package vn.luongvo.kmm.survey.domain.usecase

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.domain.repository.UserRepository
import vn.luongvo.kmm.survey.test.Fake.user
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetUserProfileUseCaseTest {

    @Mock
    private val mockRepository = mock(UserRepository::class)

    private lateinit var useCase: GetUserProfileUseCase

    @BeforeTest
    fun setUp() {
        useCase = GetUserProfileUseCaseImpl(mockRepository)
    }

    @Test
    fun `when calling getUserProfile successfully - it returns user`() = runTest {
        given(mockRepository)
            .function(mockRepository::getUserProfile)
            .whenInvoked()
            .thenReturn(flowOf(user))

        useCase().test {
            awaitItem() shouldBe user
            awaitComplete()
        }
    }

    @Test
    fun `when calling getUserProfile fails - it throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockRepository)
            .function(mockRepository::getUserProfile)
            .whenInvoked()
            .thenReturn(
                flow { throw throwable }
            )

        useCase().test {
            awaitError() shouldBe throwable
        }
    }
}

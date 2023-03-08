package vn.luongvo.kmm.survey.domain.usecase

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.domain.repository.SurveyRepository
import vn.luongvo.kmm.survey.test.Fake.surveys
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetCachedSurveysUseCaseTest {

    @Mock
    private val mockRepository = mock(SurveyRepository::class)

    private lateinit var useCase: GetCachedSurveysUseCase

    @BeforeTest
    fun setUp() {
        useCase = GetCachedSurveysUseCaseImpl(mockRepository)
    }

    @Test
    fun `when calling getCachedSurveys successfully - it returns survey list`() = runTest {
        given(mockRepository)
            .function(mockRepository::getCachedSurveys)
            .whenInvoked()
            .thenReturn(flowOf(surveys))

        useCase().test {
            awaitItem() shouldBe surveys
            awaitComplete()
        }
    }

    @Test
    fun `when calling getCachedSurveys fails - it throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockRepository)
            .function(mockRepository::getCachedSurveys)
            .whenInvoked()
            .thenReturn(
                flow { throw throwable }
            )

        useCase().test {
            awaitError() shouldBe throwable
        }
    }
}

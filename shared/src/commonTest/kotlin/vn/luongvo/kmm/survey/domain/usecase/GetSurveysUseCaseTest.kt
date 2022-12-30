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
class GetSurveysUseCaseTest {

    @Mock
    private val mockRepository = mock(SurveyRepository::class)

    private lateinit var useCase: GetSurveysUseCase

    @BeforeTest
    fun setUp() {
        useCase = GetSurveysUseCaseImpl(mockRepository)
    }

    @Test
    fun `when calling getSurveys successfully - it returns survey list`() = runTest {
        given(mockRepository)
            .function(mockRepository::getSurveys)
            .whenInvokedWith(any(), any())
            .thenReturn(flowOf(surveys))

        useCase(pageNumber = 1, pageSize = 10).test {
            awaitItem() shouldBe surveys
            awaitComplete()
        }
    }

    @Test
    fun `when calling getSurveys fails - it throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockRepository)
            .function(mockRepository::getSurveys)
            .whenInvokedWith(any(), any())
            .thenReturn(
                flow { throw throwable }
            )

        useCase(pageNumber = 1, pageSize = 10).test {
            awaitError() shouldBe throwable
        }
    }
}

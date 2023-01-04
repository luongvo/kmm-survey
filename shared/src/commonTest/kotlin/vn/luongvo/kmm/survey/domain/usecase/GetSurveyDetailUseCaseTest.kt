package vn.luongvo.kmm.survey.domain.usecase

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.domain.repository.SurveyRepository
import vn.luongvo.kmm.survey.test.Fake.surveyDetail
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetSurveyDetailUseCaseTest {

    @Mock
    private val mockRepository = mock(SurveyRepository::class)

    private lateinit var useCase: GetSurveyDetailUseCase

    @BeforeTest
    fun setUp() {
        useCase = GetSurveyDetailUseCaseImpl(mockRepository)
    }

    @Test
    fun `when calling getSurveyDetail successfully - it returns the survey detail`() = runTest {
        given(mockRepository)
            .function(mockRepository::getSurveyDetail)
            .whenInvokedWith(any())
            .thenReturn(flowOf(surveyDetail))

        useCase(id = "id").test {
            awaitItem() shouldBe surveyDetail
            awaitComplete()
        }
    }

    @Test
    fun `when calling getSurveyDetail fails - it throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockRepository)
            .function(mockRepository::getSurveyDetail)
            .whenInvokedWith(any())
            .thenReturn(
                flow { throw throwable }
            )

        useCase(id = "id").test {
            awaitError() shouldBe throwable
        }
    }
}

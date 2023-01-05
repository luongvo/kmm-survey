package vn.luongvo.kmm.survey.domain.usecase

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.domain.model.SurveySubmission
import vn.luongvo.kmm.survey.domain.repository.SurveyRepository
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class SubmitSurveyUseCaseTest {

    @Mock
    private val mockRepository = mock(SurveyRepository::class)

    private lateinit var useCase: SubmitSurveyUseCase

    @BeforeTest
    fun setUp() {
        useCase = SubmitSurveyUseCaseImpl(mockRepository)
    }

    @Test
    fun `when calling submitSurvey successfully - it returns Unit`() = runTest {
        given(mockRepository)
            .function(mockRepository::submitSurvey)
            .whenInvokedWith(any())
            .thenReturn(flowOf(Unit))

        useCase(
            submission = SurveySubmission(
                id = "id",
                questions = emptyList()
            )
        ).test {
            awaitItem() shouldBe Unit
            awaitComplete()
        }
    }

    @Test
    fun `when calling submitSurvey fails - it throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockRepository)
            .function(mockRepository::submitSurvey)
            .whenInvokedWith(any())
            .thenReturn(
                flow { throw throwable }
            )

        useCase(
            submission = SurveySubmission(
                id = "id",
                questions = emptyList()
            )
        ).test {
            awaitError() shouldBe throwable
        }
    }
}

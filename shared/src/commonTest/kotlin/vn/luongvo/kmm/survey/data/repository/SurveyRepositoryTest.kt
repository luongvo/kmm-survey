package vn.luongvo.kmm.survey.data.repository

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.data.remote.datasource.SurveyRemoteDataSource
import vn.luongvo.kmm.survey.domain.model.SurveySubmission
import vn.luongvo.kmm.survey.domain.repository.SurveyRepository
import vn.luongvo.kmm.survey.test.Fake.surveyDetail
import vn.luongvo.kmm.survey.test.Fake.surveyDetailResponse
import vn.luongvo.kmm.survey.test.Fake.surveyResponses
import vn.luongvo.kmm.survey.test.Fake.surveys
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class SurveyRepositoryTest {

    @Mock
    private val mockDataSource = mock(SurveyRemoteDataSource::class)

    private lateinit var repository: SurveyRepository

    @BeforeTest
    fun setUp() {
        repository = SurveyRepositoryImpl(
            mockDataSource
        )
    }

    @Test
    fun `when calling getSurveys successfully - it returns survey list`() = runTest {
        given(mockDataSource)
            .suspendFunction(mockDataSource::getSurveys)
            .whenInvokedWith(any(), any())
            .thenReturn(surveyResponses)

        repository.getSurveys(pageNumber = 1, pageSize = 10, isRefresh = false).test {
            awaitItem() shouldBe surveys
            awaitComplete()
        }
    }

    @Test
    fun `when calling getSurveys fails - it throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockDataSource)
            .suspendFunction(mockDataSource::getSurveys)
            .whenInvokedWith(any(), any())
            .thenThrow(throwable)

        repository.getSurveys(pageNumber = 1, pageSize = 10, isRefresh = false).test {
            awaitError() shouldBe throwable
        }
    }

    @Test
    fun `when calling getSurveyDetail successfully - it returns the survey detail`() = runTest {
        println(surveyDetailResponse)

        given(mockDataSource)
            .suspendFunction(mockDataSource::getSurveyDetail)
            .whenInvokedWith(any())
            .thenReturn(surveyDetailResponse)

        repository.getSurveyDetail(id = "id").test {
            awaitItem() shouldBe surveyDetail
            awaitComplete()
        }
    }

    @Test
    fun `when calling getSurveyDetail fails - it throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockDataSource)
            .suspendFunction(mockDataSource::getSurveyDetail)
            .whenInvokedWith(any())
            .thenThrow(throwable)

        repository.getSurveyDetail(id = "id").test {
            awaitError() shouldBe throwable
        }
    }

    @Test
    fun `when calling submitSurvey successfully - it returns Unit`() = runTest {
        given(mockDataSource)
            .suspendFunction(mockDataSource::submitSurvey)
            .whenInvokedWith(any())
            .thenReturn(Unit)

        repository.submitSurvey(
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
        given(mockDataSource)
            .suspendFunction(mockDataSource::submitSurvey)
            .whenInvokedWith(any())
            .thenThrow(throwable)

        repository.submitSurvey(
            submission = SurveySubmission(
                id = "id",
                questions = emptyList()
            )
        ).test {
            awaitError() shouldBe throwable
        }
    }
}

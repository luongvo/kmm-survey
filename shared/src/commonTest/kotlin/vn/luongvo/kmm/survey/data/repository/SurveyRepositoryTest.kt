package vn.luongvo.kmm.survey.data.repository

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.data.remote.datasource.SurveyRemoteDataSource
import vn.luongvo.kmm.survey.domain.repository.SurveyRepository
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

        repository.getSurveys(pageNumber = 1, pageSize = 10).test {
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

        repository.getSurveys(pageNumber = 1, pageSize = 10).test {
            awaitError() shouldBe throwable
        }
    }
}

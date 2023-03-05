package vn.luongvo.kmm.survey.data.repository

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.data.local.datasource.SurveyLocalDataSource
import vn.luongvo.kmm.survey.data.remote.datasource.SurveyRemoteDataSource
import vn.luongvo.kmm.survey.domain.model.SurveySubmission
import vn.luongvo.kmm.survey.domain.repository.SurveyRepository
import vn.luongvo.kmm.survey.test.Fake.cachedSurveys
import vn.luongvo.kmm.survey.test.Fake.surveyDetail
import vn.luongvo.kmm.survey.test.Fake.surveyDetailResponse
import vn.luongvo.kmm.survey.test.Fake.surveyResponses
import vn.luongvo.kmm.survey.test.Fake.surveySurveyRealmObjects
import vn.luongvo.kmm.survey.test.Fake.surveys
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class SurveyRepositoryTest {

    @Mock
    private val mockRemoteDataSource = mock(SurveyRemoteDataSource::class)

    @Mock
    private val mockLocalDataSource = mock(SurveyLocalDataSource::class)

    private lateinit var repository: SurveyRepository

    @BeforeTest
    fun setUp() {
        repository = SurveyRepositoryImpl(
            mockRemoteDataSource,
            mockLocalDataSource
        )
    }

    @Test
    fun `when calling getSurveys successfully - it caches and returns survey list`() = runTest {
        given(mockRemoteDataSource)
            .suspendFunction(mockRemoteDataSource::getSurveys)
            .whenInvokedWith(any(), any())
            .thenReturn(surveyResponses)

        repository.getSurveys(pageNumber = 1, pageSize = 10, isRefresh = false).test {
            awaitItem() shouldBe surveys
            awaitComplete()

            verify(mockLocalDataSource)
                .function(mockLocalDataSource::saveSurveys)
                .with(any())
                .wasInvoked(exactly = 1.time)
        }
    }

    @Test
    fun `when calling getSurveys fails - it does not cache and throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockRemoteDataSource)
            .suspendFunction(mockRemoteDataSource::getSurveys)
            .whenInvokedWith(any(), any())
            .thenThrow(throwable)

        repository.getSurveys(pageNumber = 1, pageSize = 10, isRefresh = false).test {
            awaitError() shouldBe throwable

            verify(mockLocalDataSource)
                .function(mockLocalDataSource::saveSurveys)
                .with(any())
                .wasInvoked(exactly = 0.time)
        }
    }

    @Test
    fun `when calling getCachedSurveys successfully - it returns cached survey list`() = runTest {
        given(mockLocalDataSource)
            .function(mockLocalDataSource::getSurveys)
            .whenInvoked()
            .thenReturn(surveySurveyRealmObjects)

        repository.getCachedSurveys().test {
            awaitItem() shouldBe cachedSurveys
            awaitComplete()
        }
    }

    @Test
    fun `when calling getCachedSurveys fails - it throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockLocalDataSource)
            .function(mockLocalDataSource::getSurveys)
            .whenInvoked()
            .thenThrow(throwable)

        repository.getCachedSurveys().test {
            awaitError() shouldBe throwable
        }
    }

    @Test
    fun `when calling getSurveyDetail successfully - it returns the survey detail`() = runTest {
        println(surveyDetailResponse)

        given(mockRemoteDataSource)
            .suspendFunction(mockRemoteDataSource::getSurveyDetail)
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
        given(mockRemoteDataSource)
            .suspendFunction(mockRemoteDataSource::getSurveyDetail)
            .whenInvokedWith(any())
            .thenThrow(throwable)

        repository.getSurveyDetail(id = "id").test {
            awaitError() shouldBe throwable
        }
    }

    @Test
    fun `when calling submitSurvey successfully - it returns Unit`() = runTest {
        given(mockRemoteDataSource)
            .suspendFunction(mockRemoteDataSource::submitSurvey)
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
        given(mockRemoteDataSource)
            .suspendFunction(mockRemoteDataSource::submitSurvey)
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

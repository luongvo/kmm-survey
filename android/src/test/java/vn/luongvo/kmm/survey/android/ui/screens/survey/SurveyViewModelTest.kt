package vn.luongvo.kmm.survey.android.ui.screens.survey

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.*
import vn.luongvo.kmm.survey.android.test.CoroutineTestRule
import vn.luongvo.kmm.survey.android.test.Fake.surveyDetail
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.screens.home.toUiModel
import vn.luongvo.kmm.survey.domain.model.*
import vn.luongvo.kmm.survey.domain.usecase.GetSurveyDetailUseCase
import vn.luongvo.kmm.survey.domain.usecase.SubmitSurveyUseCase

@ExperimentalCoroutinesApi
class SurveyViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    private val mockGetSurveyDetailUseCase: GetSurveyDetailUseCase = mockk()
    private val mockSubmitSurveyUseCase: SubmitSurveyUseCase = mockk()

    private lateinit var viewModel: SurveyViewModel

    @Before
    fun setUp() {
        every { mockGetSurveyDetailUseCase(any()) } returns flowOf(surveyDetail)
        every { mockSubmitSurveyUseCase(any()) } returns flowOf(Unit)

        viewModel = SurveyViewModel(
            mockGetSurveyDetailUseCase,
            mockSubmitSurveyUseCase
        )
    }

    @Test
    fun `when getting survey detail successfully, it shows the survey detail`() = runTest {
        viewModel.getSurveyDetail("id")

        viewModel.survey.test {
            expectMostRecentItem() shouldBe surveyDetail.toUiModel()
        }
    }

    @Test
    fun `when getting survey detail fails, it shows the corresponding error`() = runTest {
        val error = Exception()
        every { mockGetSurveyDetailUseCase(any()) } returns flow { throw error }
        viewModel.getSurveyDetail("id")

        viewModel.error.test {
            awaitItem() shouldBe error
        }
    }

    @Test
    fun `When getting survey detail, it shows and hides loading correctly`() = runTest {
        Dispatchers.setMain(StandardTestDispatcher())

        viewModel.isLoading.test {
            viewModel.getSurveyDetail("id")

            awaitItem() shouldBe false
            awaitItem() shouldBe true
            awaitItem() shouldBe false
        }
    }

    @Test
    fun `when submitting survey answers successfully, it navigates to the Completion screen`() = runTest {
        viewModel.getSurveyDetail("id")

        viewModel.navigator.test {
            viewModel.submitSurvey()

            expectMostRecentItem() shouldBe AppDestination.Completion
        }
    }

    @Test
    fun `when submitting survey answers fails, it shows the corresponding error`() = runTest {
        viewModel.getSurveyDetail("id")

        val error = Exception()
        every { mockSubmitSurveyUseCase(any()) } returns flow { throw error }
        viewModel.submitSurvey()

        viewModel.error.test {
            awaitItem() shouldBe error
        }
    }

    @Test
    fun `When submitting survey answers, it shows and hides loading correctly`() = runTest {
        viewModel.getSurveyDetail("id")
        Dispatchers.setMain(StandardTestDispatcher())

        viewModel.isLoading.test {
            viewModel.submitSurvey()

            awaitItem() shouldBe false
            awaitItem() shouldBe true
            awaitItem() shouldBe false
        }
    }

    @Test
    fun `when submitting survey answers without fetching survey successfully, it does nothing`() = runTest {
        viewModel.submitSurvey()

        verify(exactly = 0) { mockSubmitSurveyUseCase(any()) }
        viewModel.error.test {
            expectMostRecentItem() shouldBe null
        }
    }

    @Test
    fun `when answering all survey questions, it build survey submission correctly`() = runTest {
        viewModel.getSurveyDetail("id")

        val questionSubmission1 = QuestionSubmission(
            id = "question1",
            answers = mutableListOf(
                AnswerSubmission(
                    id = "answer1",
                    answer = "1"
                )
            )
        )

        viewModel.saveAnswerForQuestion(questionSubmission1)

        val listenerSlot = slot<SurveySubmission>()
        every { mockSubmitSurveyUseCase(capture(listenerSlot)) } returns flowOf(Unit)

        viewModel.submitSurvey()

        listenerSlot.captured shouldBe SurveySubmission(
            id = surveyDetail.id,
            questions = listOf(
                questionSubmission1
            )
        )
    }
}

package vn.luongvo.kmm.survey.android.ui.screens.survey

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.test.Fake.surveyDetail
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme
import vn.luongvo.kmm.survey.domain.usecase.*

@Suppress("MagicNumber")
@RunWith(AndroidJUnit4::class)
class SurveyScreenTest {

    @get:Rule
    val composeRule: ComposeContentTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private val mockGetSurveyDetailUseCase: GetSurveyDetailUseCase = mockk()
    private val mockSubmitSurveyUseCase: SubmitSurveyUseCase = mockk()

    private lateinit var viewModel: SurveyViewModel
    private var expectedAppDestination: AppDestination? = null

    @Before
    fun setup() {
        every { mockGetSurveyDetailUseCase(any()) } returns flowOf(surveyDetail)
        every { mockSubmitSurveyUseCase(any()) } returns flowOf(Unit)

        viewModel = SurveyViewModel(
            mockGetSurveyDetailUseCase,
            mockSubmitSurveyUseCase
        )
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `when entering and filling the survey answers, it shows UI correctly and navigates to the Completion screen`() =
        initComposable {
            onNodeWithContentDescription(SurveyBackButton).assertIsDisplayed()
            onNodeWithText("Scarlett Bangkok").assertIsDisplayed()
            onNodeWithText("We'd love to hear from you!").assertIsDisplayed()
            onNodeWithText(context.getString(R.string.survey_start)).assertIsDisplayed().performClick()

            testAndFillQuestionAnswers()

            expectedAppDestination shouldBe AppDestination.Completion
        }

    private fun ComposeContentTestRule.testAndFillQuestionAnswers() {
        testSurveyQuestionPage(1, "Food â€“ Variety, Taste and Presentation") {
            onAllNodesWithText("⭐️️")[0].assertIsDisplayed().performClick()
        }

        testSurveyQuestionPage(2, "Beverages â€“ Variety, Taste and Presentation") {
            onAllNodesWithText("⭐️️")[1].assertIsDisplayed().performClick()
        }

        testSurveyQuestionPage(3, "Quality of Service, Speed and Efficiency") {
            onAllNodesWithText("⭐️️")[2].assertIsDisplayed().performClick()
        }

        testSurveyQuestionPage(4, "Staff- Friendliness and Helpfulness") {
            onAllNodesWithText("⭐️️")[3].assertIsDisplayed().performClick()
        }

        testSurveyQuestionPage(5, "Restaurant Design and Atmosphere") {
            onAllNodesWithText("❤️")[4].assertIsDisplayed().performClick()
        }

        testSurveyQuestionPage(6, "Overall Satisfaction") {
            onNodeWithText("\uD83D\uDE42").assertIsDisplayed().performClick() // 🙂
        }

        testSurveyQuestionPage(7, "How did you hear about us?") {
            onNodeWithText("TripAdvisor").assertIsDisplayed().performClick()
            onNodeWithText("Website").assertIsDisplayed().performClick()
        }

        testSurveyQuestionPage(8, "How likely is that you would recommend\nScarlett to a friend or colleague?") {
            onNodeWithText("6").assertIsDisplayed().performClick()
        }

        testSurveyQuestionPage(9, "Your additional comments are welcomed.") {
            onNodeWithContentDescription(SurveyFormTextArea).assertIsDisplayed()
                .performTextInput("all good")
        }

        testSurveyQuestionPage(10, "Don't miss out on our Exclusive Promotions!") {
            onNodeWithContentDescription("${SurveyFormTextField}0").assertIsDisplayed()
                .performTextInput("lucas")
            onNodeWithContentDescription("${SurveyFormTextField}1").assertIsDisplayed()
                .performTextInput("0123456789")
            onNodeWithContentDescription("${SurveyFormTextField}2").assertIsDisplayed()
                .performTextInput("lucas@nimblehq.co")
        }

        testSurveyQuestionPage(11, "Thank you for taking the time to share your feedback!") {}
    }

    @Test
    fun `when submitting survey answers fails, it shows an error message`() {
        val expectedError = Throwable("unexpected error")
        every { mockSubmitSurveyUseCase(any()) } returns flow { throw expectedError }

        initComposable {
            onNodeWithText(context.getString(R.string.survey_start)).assertIsDisplayed().performClick()

            testAndFillQuestionAnswers()

            onNodeWithText("unexpected error").assertIsDisplayed()
        }
    }

    @Test
    fun `when clicking on the Back button, it shows the confirmation dialog to navigate back to the Home screen`() =
        initComposable {
            onNodeWithContentDescription(SurveyBackButton).performClick()
            onNodeWithText(context.getString(R.string.generic_no)).performClick()

            // Cancel and stay on the Survey screen
            expectedAppDestination shouldBe null

            onNodeWithContentDescription(SurveyBackButton).performClick()
            onNodeWithText(context.getString(R.string.generic_yes)).performClick()

            // Navigate back to the Home screen
            expectedAppDestination shouldBe AppDestination.Up
        }

    @Test
    fun `when getting survey detail fails, it shows an error message`() {
        val expectedError = Throwable("unexpected error")
        every { mockGetSurveyDetailUseCase(any()) } returns flow { throw expectedError }

        initComposable {
            onNodeWithText("unexpected error").assertIsDisplayed()
        }
    }

    private fun ComposeContentTestRule.testSurveyQuestionPage(
        index: Int,
        questionTitle: String,
        fillAnswerAction: () -> Unit
    ) {
        onNodeWithContentDescription(SurveyCloseButton + index).assertIsDisplayed()
        onNodeWithText("$index/11").assertIsDisplayed()
        onNodeWithText(questionTitle).assertIsDisplayed()

        fillAnswerAction()

        if (index < 11) {
            onNodeWithContentDescription(SurveyNextButton + index).assertIsDisplayed().performClick()
        } else {
            onNodeWithText(context.getString(R.string.survey_submit)).assertIsDisplayed().performClick()
        }
    }

    private fun initComposable(testBody: ComposeContentTestRule.() -> Unit) {
        composeRule.setContent {
            ComposeTheme {
                SurveyScreen(
                    surveyId = "123",
                    viewModel = viewModel,
                    navigator = { destination ->
                        expectedAppDestination = destination
                    }
                )
            }
        }
        testBody(composeRule)
    }
}

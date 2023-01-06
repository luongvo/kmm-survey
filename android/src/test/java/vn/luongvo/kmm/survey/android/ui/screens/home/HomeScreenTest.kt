package vn.luongvo.kmm.survey.android.ui.screens.home

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.test.Fake.surveys
import vn.luongvo.kmm.survey.android.test.Fake.user
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme
import vn.luongvo.kmm.survey.android.util.DateFormatter
import vn.luongvo.kmm.survey.domain.usecase.*

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeRule: ComposeContentTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private val mockGetUserProfileUseCase: GetUserProfileUseCase = mockk()
    private val mockGetSurveysUseCase: GetSurveysUseCase = mockk()
    private val mockDateFormatter: DateFormatter = mockk()

    private lateinit var viewModel: HomeViewModel
    private var expectedAppDestination: AppDestination? = null

    @Before
    fun setup() {
        every { mockGetUserProfileUseCase() } returns flowOf(user)
        every { mockGetSurveysUseCase(any(), any()) } returns flowOf(surveys)
        every { mockDateFormatter.format(any(), any()) } returns "Thursday, December 29"

        viewModel = HomeViewModel(
            mockGetUserProfileUseCase,
            mockGetSurveysUseCase,
            mockDateFormatter
        )
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `when entering the Home screen, it shows UI correctly`() = initComposable {
        onNodeWithText("THURSDAY, DECEMBER 29").assertIsDisplayed()
        onNodeWithText(context.getString(R.string.home_today)).assertIsDisplayed()
        onNodeWithContentDescription(HomeUserAvatar).assertIsDisplayed()
    }

    @Test
    fun `when getting user profile fails, it shows an error message`() {
        val expectedError = Throwable("unexpected error")
        every { mockGetUserProfileUseCase() } returns flow { throw expectedError }

        initComposable {
            onNodeWithText("unexpected error").assertIsDisplayed()
        }
    }

    @Test
    fun `when getting surveys successfully, it shows the survey list`() = initComposable {
        onNodeWithText("Scarlett Bangkok").assertIsDisplayed()
        onNodeWithText("We'd love to hear from you!").assertIsDisplayed()
        onNodeWithContentDescription(HomeSurveyDetail).assertIsDisplayed()

        onRoot().performTouchInput { swipeLeft() }

        onNodeWithText("ibis Bangkok Riverside").assertIsDisplayed()
        onNodeWithText("We'd like to hear from you!").assertIsDisplayed()

        onRoot().performTouchInput { swipeRight() }

        onNodeWithText("Scarlett Bangkok").assertIsDisplayed()
        onNodeWithText("We'd love to hear from you!").assertIsDisplayed()
    }

    @Test
    fun `when getting surveys fails, it shows an error message`() {
        val expectedError = Throwable("unexpected error")
        every { mockGetSurveysUseCase(any(), any()) } returns flow { throw expectedError }

        initComposable {
            onNodeWithText("unexpected error").assertIsDisplayed()
        }
    }

    @Test
    fun `when clicking on the Next button on each survey, it navigates to the Survey screen`() = initComposable {
        onNodeWithContentDescription(HomeSurveyDetail).performClick()

        assertEquals(expectedAppDestination, AppDestination.Survey)
    }

    private fun initComposable(testBody: ComposeContentTestRule.() -> Unit) {
        composeRule.setContent {
            ComposeTheme {
                HomeScreen(
                    viewModel = viewModel,
                    navigator = { destination ->
                        expectedAppDestination = destination
                    },
                    onDrawerUiStateChange = {},
                    onOpenDrawer = {}
                )
            }
        }
        testBody(composeRule)
    }
}

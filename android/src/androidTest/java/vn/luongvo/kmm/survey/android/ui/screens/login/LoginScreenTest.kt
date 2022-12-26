package vn.luongvo.kmm.survey.android.ui.screens.login

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.*
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.screens.MainActivity
import vn.luongvo.kmm.survey.domain.usecase.IsLoggedInUseCase
import vn.luongvo.kmm.survey.domain.usecase.LogInUseCase

@ExperimentalTestApi
class LoginScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val mockIsLoggedInUseCase: IsLoggedInUseCase = mockk()
    private val mockLogInUseCase: LogInUseCase = mockk()

    private lateinit var viewModel: LoginViewModel
    private var expectedAppDestination: AppDestination? = null

    @Before
    fun setup() {
        every { mockIsLoggedInUseCase() } returns flowOf(false)
        every { mockLogInUseCase(any(), any()) } returns flowOf(mockk())

        viewModel = LoginViewModel(
            mockIsLoggedInUseCase,
            mockLogInUseCase
        )
        composeRule.activity.setContent {
            LoginScreen(
                viewModel = viewModel,
                navigator = { destination -> expectedAppDestination = destination }
            )
        }
    }

    @Test
    fun when_entering_the_Login_screen__it_shows_UI_correctly() {
        composeRule.run {
            waitForAnimationEnd()

            onNodeWithText(activity.getString(R.string.login_email)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.login_password)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.login_forgot)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.login_button)).assertIsDisplayed()
        }
    }

    @Test
    fun when_entering_the_Login_screen_with_logged_in_status__it_navigates_to_Home_screen() {
        every { mockIsLoggedInUseCase() } returns flowOf(true)
        composeRule.run {
            waitForIdle()
            assertEquals(expectedAppDestination, AppDestination.Home)
        }
    }

    @Test
    fun when_logging_in_successfully__it_navigates_to_Home_screen() {
        composeRule.run {
            waitForAnimationEnd()

            onNodeWithContentDescription(LoginEmailField).performTextInput("luong@nimblehq.co")
            onNodeWithContentDescription(LoginPasswordField).performTextInput("12345678")
            onNodeWithContentDescription(LoginButton).performClick()

            assertEquals(expectedAppDestination, AppDestination.Home)
        }
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.waitForAnimationEnd() {
        mainClock.advanceTimeBy(800 + 500 + 700)
    }
}

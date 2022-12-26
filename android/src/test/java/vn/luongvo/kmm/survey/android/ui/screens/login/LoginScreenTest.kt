package vn.luongvo.kmm.survey.android.ui.screens.login

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
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.domain.exceptions.ApiException
import vn.luongvo.kmm.survey.domain.usecase.IsLoggedInUseCase
import vn.luongvo.kmm.survey.domain.usecase.LogInUseCase

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeRule: ComposeContentTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

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
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun when_entering_the_Login_screen__it_shows_UI_correctly() = initComposable {
        waitForAnimationEnd()

        onNodeWithText(context.getString(R.string.login_email)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.login_password)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.login_forgot)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.login_button)).assertIsDisplayed()
    }

    @Test
    fun when_entering_the_Login_screen_with_logged_in_status__it_navigates_to_Home_screen() {
        every { mockIsLoggedInUseCase() } returns flowOf(true)
        initComposable {
            waitForIdle()
            assertEquals(expectedAppDestination, AppDestination.Home)
        }
    }

    @Test
    fun when_logging_in_successfully__it_navigates_to_Home_screen() = initComposable {
        waitForAnimationEnd()

        onNodeWithContentDescription(LoginEmailField).performTextInput("luong@nimblehq.co")
        onNodeWithContentDescription(LoginPasswordField).performTextInput("12345678")
        onNodeWithContentDescription(LoginButton).performClick()

        assertEquals(expectedAppDestination, AppDestination.Home)
    }

    @Test
    fun when_logging_in_fails__it_shows_the_error_dialog() = initComposable {
        val expectedError = ApiException(
            message = "Your email or password is incorrect. Please try again.",
            code = "code"
        )
        every { mockLogInUseCase(any(), any()) } returns flow { throw expectedError }

        waitForAnimationEnd()

        onNodeWithContentDescription(LoginEmailField).performTextInput("luong@nimblehq.co")
        onNodeWithContentDescription(LoginPasswordField).performTextInput("1234567")
        onNodeWithContentDescription(LoginButton).performClick()
        waitForIdle()

        onNodeWithText(expectedError.message ?: "").assertIsDisplayed()
        onNodeWithText(context.getString(R.string.generic_ok)).assertIsDisplayed().performClick()
    }

    private fun initComposable(testBody: ComposeContentTestRule.() -> Unit) {
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel,
                navigator = { destination -> expectedAppDestination = destination }
            )
        }
        testBody.invoke(composeRule)
    }

    private fun ComposeContentTestRule.waitForAnimationEnd() {
        mainClock.advanceTimeBy(800 + 500 + 700)
    }
}

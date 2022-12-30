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
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme
import vn.luongvo.kmm.survey.domain.exceptions.ApiException
import vn.luongvo.kmm.survey.domain.usecase.IsLoggedInUseCase
import vn.luongvo.kmm.survey.domain.usecase.LogInUseCase

private const val LoginAnimationDurationInMilliseconds =
    FirstPhaseDurationInMilliseconds + StayPhaseDurationInMilliseconds + LastPhaseDurationInMilliseconds

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
    fun `when entering the Login screen, it shows UI correctly`() = initComposable {
        waitForAnimationEnd()

        onNodeWithText(context.getString(R.string.login_email)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.login_password)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.login_forgot)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.login_button)).assertIsDisplayed()
    }

    @Test
    fun `when entering the Login screen with logged in status, it navigates to the Home screen`() {
        every { mockIsLoggedInUseCase() } returns flowOf(true)
        initComposable {
            waitForIdle()
            assertEquals(expectedAppDestination, AppDestination.Home)
        }
    }

    @Test
    fun `when logging in successfully, it navigates to the Home screen`() = initComposable {
        waitForAnimationEnd()

        onNodeWithContentDescription(LoginEmailField).performTextInput("luong@nimblehq.co")
        onNodeWithContentDescription(LoginPasswordField).performTextInput("12345678")
        onNodeWithContentDescription(LoginButton).performClick()

        assertEquals(expectedAppDestination, AppDestination.Home)
    }

    @Test
    fun `when logging in fails, it shows an error dialog`() = initComposable {
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

        onNodeWithText(expectedError.message.orEmpty()).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.generic_ok)).assertIsDisplayed().performClick()
    }

    private fun initComposable(testBody: ComposeContentTestRule.() -> Unit) {
        composeRule.setContent {
            ComposeTheme {
                LoginScreen(
                    viewModel = viewModel,
                    navigator = { destination ->
                        expectedAppDestination = destination
                    }
                )
            }
        }
        testBody.invoke(composeRule)
    }

    private fun ComposeContentTestRule.waitForAnimationEnd() {
        mainClock.advanceTimeBy(LoginAnimationDurationInMilliseconds.toLong())
    }
}

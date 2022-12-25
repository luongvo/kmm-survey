package vn.luongvo.kmm.survey.android.ui.screens.login

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.*
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.screens.MainActivity
import vn.luongvo.kmm.survey.domain.usecase.IsLoggedInUseCase
import vn.luongvo.kmm.survey.domain.usecase.LogInUseCase

@Suppress("IllegalIdentifier") // https://issuetracker.google.com/issues/208842981
@ExperimentalTestApi
class LoginScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val mockIsLoggedInUseCase: IsLoggedInUseCase = mockk()
    private val mockLogInUseCase: LogInUseCase = mockk()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        every { mockIsLoggedInUseCase() } returns flowOf(false)
        every { mockLogInUseCase(any(), any()) } returns flowOf(mockk())

        viewModel = LoginViewModel(
            mockIsLoggedInUseCase,
            mockLogInUseCase
        )
        composeRule.activity.setContent {
            LoginScreen(viewModel = viewModel, navigator = {})
        }
    }

    @Test
    fun `when entering the Login screen - it shows UI correctly`() {
        composeRule.apply {
            mainClock.advanceTimeBy(800 + 500 + 700)

            onNodeWithText(activity.getString(R.string.login_email)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.login_password)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.login_forgot)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.login_button)).assertIsDisplayed()
        }
    }
}

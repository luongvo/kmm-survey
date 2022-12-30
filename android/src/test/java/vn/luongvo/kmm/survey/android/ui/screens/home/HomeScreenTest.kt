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
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.test.Fake
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

    @Before
    fun setup() {
        every { mockGetUserProfileUseCase() } returns flowOf(Fake.user)
        every { mockGetSurveysUseCase(any(), any()) } returns flowOf(Fake.surveys)
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

    private fun initComposable(testBody: ComposeContentTestRule.() -> Unit) {
        composeRule.setContent {
            HomeScreen(
                viewModel = viewModel
            )
        }
        testBody.invoke(composeRule)
    }
}

package vn.luongvo.kmm.survey.android.ui.screens.home

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.*
import vn.luongvo.kmm.survey.android.test.CoroutineTestRule
import vn.luongvo.kmm.survey.android.test.Fake.surveys
import vn.luongvo.kmm.survey.android.test.Fake.user
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.util.DateFormatter
import vn.luongvo.kmm.survey.domain.usecase.GetSurveysUseCase
import vn.luongvo.kmm.survey.domain.usecase.GetUserProfileUseCase

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    private val mockGetUserProfileUseCase: GetUserProfileUseCase = mockk()
    private val mockGetSurveysUseCase: GetSurveysUseCase = mockk()
    private val mockDateFormatter: DateFormatter = mockk()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        every { mockGetUserProfileUseCase() } returns flowOf(user)
        every { mockGetSurveysUseCase(any(), any()) } returns flowOf(surveys)
        every { mockDateFormatter.format(any(), any()) } returns "Thursday, December 29"

        viewModel = HomeViewModel(
            mockGetUserProfileUseCase,
            mockGetSurveysUseCase,
            mockDateFormatter
        )
    }

    @Test
    fun `when loading Home screen, it shows the current date time`() = runTest {
        viewModel.init()

        viewModel.currentDate.test {
            expectMostRecentItem() shouldBe "Thursday, December 29"
        }
    }

    @Test
    fun `when getting user profile successfully, it shows the user info`() = runTest {
        viewModel.init()

        viewModel.user.test {
            expectMostRecentItem() shouldBe user.toUiModel()
        }
    }

    @Test
    fun `when getting user profile fails, it shows the corresponding error`() = runTest {
        val error = Exception()
        every { mockGetUserProfileUseCase() } returns flow { throw error }
        viewModel.init()

        viewModel.error.test {
            awaitItem() shouldBe error
        }
    }

    @Test
    fun `when getting surveys successfully, it shows the survey list`() = runTest {
        viewModel.init()

        viewModel.surveys.test {
            expectMostRecentItem() shouldBe surveys.map { it.toUiModel() }
        }
    }

    @Test
    fun `when getting surveys fails, it shows the corresponding error`() = runTest {
        val error = Exception()
        every { mockGetSurveysUseCase(any(), any()) } returns flow { throw error }
        viewModel.init()

        viewModel.error.test {
            awaitItem() shouldBe error
        }
    }

    @Test
    fun `When getting surveys, it shows and hides loading correctly`() = runTest {
        Dispatchers.setMain(StandardTestDispatcher())

        viewModel.isLoading.test {
            viewModel.init()

            awaitItem() shouldBe false
            awaitItem() shouldBe true
            awaitItem() shouldBe false
        }
    }

    @Test
    fun `when clicking on the Next button on each survey, it navigates to the Survey screen`() = runTest {
        viewModel.navigator.test {
            viewModel.navigateToSurvey("id")

            expectMostRecentItem() shouldBe AppDestination.Survey
        }
    }
}

package vn.luongvo.kmm.survey.android.ui.screens.survey

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
import vn.luongvo.kmm.survey.android.test.Fake.survey
import vn.luongvo.kmm.survey.android.ui.screens.home.toUiModel
import vn.luongvo.kmm.survey.domain.usecase.GetSurveyDetailUseCase

@ExperimentalCoroutinesApi
class SurveyViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    private val mockGetSurveyDetailUseCase: GetSurveyDetailUseCase = mockk()

    private lateinit var viewModel: SurveyViewModel

    @Before
    fun setUp() {
        every { mockGetSurveyDetailUseCase(any()) } returns flowOf(survey)

        viewModel = SurveyViewModel(
            mockGetSurveyDetailUseCase
        )
    }

    @Test
    fun `when getting survey detail successfully, it shows the survey detail`() = runTest {
        viewModel.getSurveyDetail("id")

        viewModel.survey.test {
            expectMostRecentItem() shouldBe survey.toUiModel()
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
}

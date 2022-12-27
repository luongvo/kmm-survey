package vn.luongvo.kmm.survey.android.ui.screens.login

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
import vn.luongvo.kmm.survey.android.test.Fake.token
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.domain.usecase.IsLoggedInUseCase
import vn.luongvo.kmm.survey.domain.usecase.LogInUseCase

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    private val mockIsLoggedInUseCase: IsLoggedInUseCase = mockk()
    private val mockLogInUseCase: LogInUseCase = mockk()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        every { mockIsLoggedInUseCase() } returns flowOf(false)
        every { mockLogInUseCase(any(), any()) } returns flowOf(token)

        viewModel = LoginViewModel(
            mockIsLoggedInUseCase,
            mockLogInUseCase
        )
    }

    @Test
    fun `when checking user logged in successfully with false, it shows login page`() = runTest {
        viewModel.init()

        viewModel.isLoggedIn.value shouldBe false
    }

    @Test
    fun `when checking user logged in successfully with true, it navigates to Home screen`() = runTest {
        every { mockIsLoggedInUseCase() } returns flowOf(true)
        viewModel.navigator.test {
            viewModel.init()

            viewModel.isLoggedIn.value shouldBe true
            expectMostRecentItem() shouldBe AppDestination.Home
        }
    }

    @Test
    fun `when calling logIn successfully, it navigates to Home screen`() = runTest {
        viewModel.navigator.test {
            viewModel.logIn("email", "password")

            viewModel.isLoggedIn.value shouldBe true
            expectMostRecentItem() shouldBe AppDestination.Home
        }
    }

    @Test
    fun `when calling logIn fails, it emits error`() = runTest {
        val error = Exception()
        every { mockLogInUseCase(any(), any()) } returns flow { throw error }
        viewModel.logIn("email", "password")

        viewModel.error shouldBe error
    }

    @Test
    fun `When calling logIn, it shows and hides loading correctly`() = runTest {
        Dispatchers.setMain(StandardTestDispatcher())

        viewModel.isLoading.test {
            viewModel.logIn("email", "password")

            awaitItem() shouldBe false
            awaitItem() shouldBe true
            awaitItem() shouldBe false
        }
    }
}

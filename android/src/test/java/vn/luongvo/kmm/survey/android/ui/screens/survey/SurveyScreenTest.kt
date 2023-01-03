package vn.luongvo.kmm.survey.android.ui.screens.survey

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme
import vn.luongvo.kmm.survey.domain.usecase.*

@RunWith(AndroidJUnit4::class)
class SurveyScreenTest {

    @get:Rule
    val composeRule: ComposeContentTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private var expectedAppDestination: AppDestination? = null

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `when entering the Survey screen, it shows UI correctly`() = initComposable {
        onNodeWithContentDescription(SurveyBackButton).assertIsDisplayed()

        onNodeWithText("Scarlett Bangkok").assertIsDisplayed()
        onNodeWithText("We'd love to hear from you!").assertIsDisplayed()

        onNodeWithText(context.getString(R.string.survey_start)).assertIsDisplayed()
    }

    @Test
    fun `when clicking on the Back button, it navigates back to the Home screen`() = initComposable {
        onNodeWithContentDescription(SurveyBackButton).performClick()

        assertEquals(expectedAppDestination, AppDestination.Up)
    }

    private fun initComposable(testBody: ComposeContentTestRule.() -> Unit) {
        composeRule.setContent {
            ComposeTheme {
                SurveyScreen(
                    surveyId = "123",
                    navigator = { destination -> expectedAppDestination = destination }
                )
            }
        }
        testBody(composeRule)
    }
}

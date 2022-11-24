package vn.luongvo.kmm.survey.android.ui.screens

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Rule
import vn.luongvo.kmm.survey.android.test.CoroutineTestRule
import vn.luongvo.kmm.survey.android.test.runBlockingTest

@ExperimentalCoroutinesApi
abstract class BaseViewModelTest {

    @get:Rule
    private var coroutineRule = CoroutineTestRule()

    protected fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
        coroutineRule.runBlockingTest(block)

    protected val testDispatcherProvider = coroutineRule.testDispatcherProvider

    protected val testDispatcher = coroutineRule.testDispatcher
}

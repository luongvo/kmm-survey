package vn.luongvo.kmm.survey.android.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*
import vn.luongvo.kmm.survey.android.lib.IsLoading
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination

interface BaseInput

interface BaseOutput

@Suppress("PropertyName")
abstract class BaseViewModel : ViewModel() {

    // TODO update in https://github.com/luongvo/kmm-survey/issues/8
//    abstract val input: BaseInput
//
//    abstract val output: BaseOutput

    private var loadingCount: Int = 0

    private val _showLoading = MutableStateFlow(false)
    val showLoading: StateFlow<IsLoading>
        get() = _showLoading

    protected val _error = MutableSharedFlow<Throwable>()
    val error: SharedFlow<Throwable>
        get() = _error

    protected val _navigator = MutableSharedFlow<AppDestination>()
    val navigator: SharedFlow<AppDestination>
        get() = _navigator

    /**
     * To show loading manually, should call `hideLoading` after
     */
    protected fun showLoading() {
        if (loadingCount == 0) {
            _showLoading.value = true
        }
        loadingCount++
    }

    /**
     * To hide loading manually, should be called after `showLoading`
     */
    protected fun hideLoading() {
        loadingCount--
        if (loadingCount == 0) {
            _showLoading.value = false
        }
    }

    // TODO update in https://github.com/luongvo/kmm-survey/issues/8
//    fun execute(coroutineDispatcher: CoroutineDispatcher = dispatchersProvider.io, job: suspend () -> Unit) =
//        viewModelScope.launch(coroutineDispatcher) {
//            job.invoke()
//        }
}

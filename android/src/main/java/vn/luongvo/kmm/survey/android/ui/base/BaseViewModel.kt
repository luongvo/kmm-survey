package vn.luongvo.kmm.survey.android.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<IsLoading>
        get() = _isLoading

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
            _isLoading.value = true
        }
        loadingCount++
    }

    /**
     * To hide loading manually, should be called after `showLoading`
     */
    protected fun hideLoading() {
        loadingCount--
        if (loadingCount == 0) {
            _isLoading.value = false
        }
    }

    protected fun launch(job: suspend () -> Unit) =
        viewModelScope.launch {
            job.invoke()
        }
}

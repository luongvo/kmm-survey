package vn.luongvo.kmm.survey.android.ui.base

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import vn.luongvo.kmm.survey.android.lib.IsLoading
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination

@Suppress("PropertyName")
abstract class BaseViewModel : ViewModel() {

    private var loadingCount: Int = 0

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<IsLoading> = _isLoading

    protected val _error = MutableStateFlow<Throwable?>(null)
    val error: StateFlow<Throwable?> = _error

    protected val _navigator = MutableSharedFlow<AppDestination>()
    val navigator: SharedFlow<AppDestination> = _navigator

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

    protected fun <T> Flow<T>.injectLoading(): Flow<T> = this
        .onStart { showLoading() }
        .onCompletion { hideLoading() }

    fun clearError() {
        viewModelScope.launch { _error.emit(null) }
    }
}

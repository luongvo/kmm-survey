package vn.luongvo.kmm.survey.android.ui.screens.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import vn.luongvo.kmm.survey.android.ui.base.BaseViewModel
import vn.luongvo.kmm.survey.domain.usecase.LogInUseCase

class LoginViewModel(
    private val logInUseCase: LogInUseCase
) : BaseViewModel() {

    fun logIn() {
        // TODO sample request to verify network layer implementation
        viewModelScope.launch {
            logInUseCase(
                email = "luong@nimblehq.co",
                password = "12345678"
            )
                .catch { e ->
                    Timber.e(e)
                }
                .collect {
                    Timber.d(it.toString())
                }
        }
    }
}

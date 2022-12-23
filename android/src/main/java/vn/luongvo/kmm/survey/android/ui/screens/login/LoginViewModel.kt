package vn.luongvo.kmm.survey.android.ui.screens.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import vn.luongvo.kmm.survey.android.ui.base.BaseViewModel
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.domain.usecase.LogInUseCase

class LoginViewModel(
    private val logInUseCase: LogInUseCase
) : BaseViewModel() {

    fun logIn(email: String, password: String) {
        logInUseCase(
            email = email,
            password = password
        )
            .injectLoading()
            .catch { e -> _error.emit(e) }
            .onEach {
                _navigator.emit(AppDestination.Home)
            }
            .launchIn(viewModelScope)
    }
}

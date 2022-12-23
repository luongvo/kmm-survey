package vn.luongvo.kmm.survey.android.ui.screens.login

import kotlinx.coroutines.flow.*
import vn.luongvo.kmm.survey.android.ui.base.BaseViewModel
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.domain.usecase.LogInUseCase

class LoginViewModel(
    private val logInUseCase: LogInUseCase
) : BaseViewModel() {

    fun logIn(email: String, password: String) = launch {
        logInUseCase(
            email = email,
            password = password
        )
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .catch { e ->
                _error.emit(e)
            }
            .collect {
                _navigator.emit(AppDestination.Home)
            }
    }
}

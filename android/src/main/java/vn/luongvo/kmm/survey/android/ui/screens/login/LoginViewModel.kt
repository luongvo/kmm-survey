package vn.luongvo.kmm.survey.android.ui.screens.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import vn.luongvo.kmm.survey.android.ui.base.BaseViewModel
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.domain.usecase.IsLoggedInUseCase
import vn.luongvo.kmm.survey.domain.usecase.LogInUseCase

class LoginViewModel(
    private val isLoggedInUseCase: IsLoggedInUseCase,
    private val logInUseCase: LogInUseCase
) : BaseViewModel() {

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> = _isLoggedIn

    fun init() {
        isLoggedInUseCase()
            .onEach { isLoggedIn ->
                _isLoggedIn.emit(isLoggedIn)
                if (isLoggedIn) {
                    navigateToHome()
                }
            }
            .launchIn(viewModelScope)
    }

    fun logIn(email: String, password: String) {
        logInUseCase(
            email = email,
            password = password
        )
            .injectLoading()
            .catch { e -> error = e }
            .onEach {
                _isLoggedIn.emit(true)
                navigateToHome()
            }
            .launchIn(viewModelScope)
    }

    private suspend fun navigateToHome() {
        _navigator.emit(AppDestination.Home)
    }
}

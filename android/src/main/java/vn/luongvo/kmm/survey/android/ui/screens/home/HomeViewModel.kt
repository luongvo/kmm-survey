package vn.luongvo.kmm.survey.android.ui.screens.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import timber.log.Timber
import vn.luongvo.kmm.survey.android.ui.base.BaseViewModel
import vn.luongvo.kmm.survey.domain.usecase.GetUserProfileUseCase

class HomeViewModel(
    getUserProfileUseCase: GetUserProfileUseCase,
//    tokenLocalDataSource: TokenLocalDataSource
) : BaseViewModel() {

    init {
        // TODO test code for POW, will remove it in the next PR
//        tokenLocalDataSource.run {
//            saveToken(
//                Token(
//                    tokenType,
//                    "invalid_access_token",
//                    refreshToken
//                )
//            )
//        }

        getUserProfileUseCase()
            .catch { e -> error = e }
            .onEach {
                // TODO Integrate getting user profile in https://github.com/luongvo/kmm-survey/issues/13
                Timber.d(it.toString())
            }
            .launchIn(viewModelScope)
    }
}

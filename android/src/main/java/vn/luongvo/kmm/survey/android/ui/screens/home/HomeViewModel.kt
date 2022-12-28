package vn.luongvo.kmm.survey.android.ui.screens.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import vn.luongvo.kmm.survey.android.ui.base.BaseViewModel
import vn.luongvo.kmm.survey.android.util.DateFormatter
import vn.luongvo.kmm.survey.domain.usecase.GetSurveysUseCase
import vn.luongvo.kmm.survey.domain.usecase.GetUserProfileUseCase
import java.util.*

private const val HeaderDateFormat = "EEEE, MMMM d"

class HomeViewModel(
    getUserProfileUseCase: GetUserProfileUseCase,
    getSurveysUseCase: GetSurveysUseCase,
    dateFormatter: DateFormatter
) : BaseViewModel() {

    private val _currentDate = MutableStateFlow("")
    val currentDate: StateFlow<String> = _currentDate

    private val _avatarUrl = MutableStateFlow("")
    val avatarUrl: StateFlow<String> = _avatarUrl

    init {
        viewModelScope.launch {
            _currentDate.emit(
                dateFormatter.format(Calendar.getInstance().time, HeaderDateFormat)
            )
        }

        getUserProfileUseCase()
            .catch { e -> error = e }
            .onEach {
                _avatarUrl.emit(it.avatarUrl)
            }
            .launchIn(viewModelScope)

        getSurveysUseCase(pageNumber = 1, pageSize = 10)
            .catch { e -> error = e }
            .onEach {
                // TODO Integrate getting user profile in https://github.com/luongvo/kmm-survey/issues/16
                Timber.d(it.toString())
            }
            .launchIn(viewModelScope)
    }
}

package vn.luongvo.kmm.survey.android.ui.screens.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import vn.luongvo.kmm.survey.android.BuildConfig
import vn.luongvo.kmm.survey.android.ui.base.BaseViewModel
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.util.DateFormatter
import vn.luongvo.kmm.survey.domain.usecase.*
import java.util.*

private const val HeaderDateFormat = "EEEE, MMMM d"
private const val SurveyStartPageIndex = 1
private const val SurveyPageSize = 10

class HomeViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getSurveysUseCase: GetSurveysUseCase,
    private val getCachedSurveysUseCase: GetCachedSurveysUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val dateFormatter: DateFormatter
) : BaseViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private val _currentDate = MutableStateFlow("")
    val currentDate: StateFlow<String> = _currentDate

    private val _appVersion = MutableStateFlow("")
    val appVersion: StateFlow<String> = _appVersion

    private val _user = MutableStateFlow<UserUiModel?>(null)
    val user: StateFlow<UserUiModel?> = _user

    private val _surveys = MutableStateFlow(emptyList<SurveyUiModel>())
    val surveys: StateFlow<List<SurveyUiModel>> = _surveys

    fun init() {
        viewModelScope.launch {
            _currentDate.emit(
                dateFormatter.format(Calendar.getInstance().time, HeaderDateFormat)
            )
            _appVersion.emit("v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
        }

        loadCachedSurveys()
        loadData()
    }

    private fun loadCachedSurveys() {
        getCachedSurveysUseCase()
            .injectLoading()
            .onEach { surveys ->
                _surveys.emit(surveys.map { it.toUiModel() })
            }
            .launchIn(viewModelScope)
    }

    fun loadData(isRefresh: Boolean = false) {
        getUserProfileUseCase()
            .catch { e -> _error.emit(e) }
            .onEach {
                _user.emit(it.toUiModel())
            }
            .launchIn(viewModelScope)

        getSurveysUseCase(
            pageNumber = SurveyStartPageIndex,
            pageSize = SurveyPageSize,
            isRefresh = isRefresh
        )
            .onStart {
                if (isRefresh) _isRefreshing.value = true
                else if (_surveys.value.isEmpty()) {
                    // If there is no cached survey data, shows the shimmer loading when fetching remote data.
                    showLoading()
                }
            }
            .onCompletion {
                if (isRefresh) _isRefreshing.value = false
                else hideLoading()
            }
            .catch { e -> _error.emit(e) }
            .onEach { surveys ->
                _surveys.emit(surveys.map { it.toUiModel() })
            }
            .launchIn(viewModelScope)
    }

    fun navigateToSurvey(surveyId: String) {
        viewModelScope.launch {
            _navigator.emit(AppDestination.Survey.buildDestination(surveyId))
        }
    }

    fun logOut() {
        logOutUseCase()
            .catch { e -> _error.emit(e) }
            .onEach {
                _navigator.emit(AppDestination.Login)
            }
            .launchIn(viewModelScope)
    }
}

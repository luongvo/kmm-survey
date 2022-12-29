package vn.luongvo.kmm.survey.android.ui.screens.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import vn.luongvo.kmm.survey.android.ui.base.BaseViewModel
import vn.luongvo.kmm.survey.android.util.DateFormatter
import vn.luongvo.kmm.survey.domain.usecase.GetSurveysUseCase
import vn.luongvo.kmm.survey.domain.usecase.GetUserProfileUseCase
import java.util.*

private const val HeaderDateFormat = "EEEE, MMMM d"
private const val SurveyStartPageIndex = 1
private const val SurveyPageSize = 10

class HomeViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getSurveysUseCase: GetSurveysUseCase,
    private val dateFormatter: DateFormatter
) : BaseViewModel() {

    private val _currentDate = MutableStateFlow("")
    val currentDate: StateFlow<String> = _currentDate

    private val _avatarUrl = MutableStateFlow("")
    val avatarUrl: StateFlow<String> = _avatarUrl

    private val _surveys = MutableStateFlow(emptyList<SurveyUiModel>())
    val surveys: StateFlow<List<SurveyUiModel>> = _surveys

    fun init() {
        viewModelScope.launch {
            _currentDate.emit(
                dateFormatter.format(Calendar.getInstance().time, HeaderDateFormat)
            )
        }

        getUserProfileUseCase()
            .catch { e -> _error.emit(e) }
            .onEach {
                _avatarUrl.emit(it.avatarUrl)
            }
            .launchIn(viewModelScope)

        getSurveysUseCase(pageNumber = SurveyStartPageIndex, pageSize = SurveyPageSize)
            .catch { e -> _error.emit(e) }
            .onEach { surveys ->
                _surveys.emit(surveys.map { it.toUiModel() })
            }
            .launchIn(viewModelScope)
    }
}

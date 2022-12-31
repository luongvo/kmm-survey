package vn.luongvo.kmm.survey.android.ui.screens.survey

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import vn.luongvo.kmm.survey.android.ui.base.BaseViewModel
import vn.luongvo.kmm.survey.android.ui.screens.home.SurveyUiModel
import vn.luongvo.kmm.survey.android.ui.screens.home.toUiModel
import vn.luongvo.kmm.survey.domain.usecase.GetSurveyDetailUseCase

class SurveyViewModel(
    private val getSurveyDetailUseCase: GetSurveyDetailUseCase
) : BaseViewModel() {

    private val _survey = MutableStateFlow<SurveyUiModel?>(null)
    val survey: StateFlow<SurveyUiModel?> = _survey

    fun getSurveyDetail(id: String) {
        getSurveyDetailUseCase(id = id)
            .injectLoading()
            .catch { e -> _error.emit(e) }
            .onEach { survey ->
                _survey.emit(survey.toUiModel())
            }
            .launchIn(viewModelScope)
    }
}

package vn.luongvo.kmm.survey.android.ui.screens.survey

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import timber.log.Timber
import vn.luongvo.kmm.survey.android.ui.base.BaseViewModel
import vn.luongvo.kmm.survey.domain.usecase.GetSurveyDetailUseCase

class SurveyViewModel(
    private val getSurveyDetailUseCase: GetSurveyDetailUseCase
) : BaseViewModel() {

    fun getSurveyDetail(id: String) {
        // TODO integrate in https://github.com/luongvo/kmm-survey/issues/23
        getSurveyDetailUseCase(id = id)
            .injectLoading()
            .catch { e -> _error.emit(e) }
            .onEach { survey ->
                Timber.d(survey.toString())
            }
            .launchIn(viewModelScope)
    }
}

package vn.luongvo.kmm.survey.android.ui.screens.survey

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import vn.luongvo.kmm.survey.android.ui.base.BaseViewModel
import vn.luongvo.kmm.survey.android.ui.screens.home.SurveyUiModel
import vn.luongvo.kmm.survey.android.ui.screens.home.toUiModel
import vn.luongvo.kmm.survey.domain.model.*
import vn.luongvo.kmm.survey.domain.usecase.GetSurveyDetailUseCase
import vn.luongvo.kmm.survey.domain.usecase.SubmitSurveyUseCase

class SurveyViewModel(
    private val getSurveyDetailUseCase: GetSurveyDetailUseCase,
    private val submitSurveyUseCase: SubmitSurveyUseCase
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

    fun submitSurvey() {
        _survey.value?.let { survey ->
            // TODO integrate in https://github.com/luongvo/kmm-survey/issues/34
            val surveySubmission = SurveySubmission(
                id = survey.id,
                questions = listOf(
                    QuestionSubmission(
                        id = survey.questions[1].id,
                        answers = listOf(
                            AnswerSubmission(
                                id = survey.questions[1].answers[0].id,
                                answer = "answer"
                            )
                        )
                    )
                )
            )

            submitSurveyUseCase(surveySubmission)
                .injectLoading()
                .catch { e -> _error.emit(e) }
                .onEach {
                    // TODO navigate to the Completion screen
                }
                .launchIn(viewModelScope)
        }
    }
}

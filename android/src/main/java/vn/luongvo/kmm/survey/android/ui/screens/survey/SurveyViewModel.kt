package vn.luongvo.kmm.survey.android.ui.screens.survey

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import vn.luongvo.kmm.survey.android.ui.base.BaseViewModel
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.screens.home.SurveyUiModel
import vn.luongvo.kmm.survey.android.ui.screens.home.toUiModel
import vn.luongvo.kmm.survey.domain.model.QuestionSubmission
import vn.luongvo.kmm.survey.domain.model.SurveySubmission
import vn.luongvo.kmm.survey.domain.usecase.GetSurveyDetailUseCase
import vn.luongvo.kmm.survey.domain.usecase.SubmitSurveyUseCase

class SurveyViewModel(
    private val getSurveyDetailUseCase: GetSurveyDetailUseCase,
    private val submitSurveyUseCase: SubmitSurveyUseCase
) : BaseViewModel() {

    private val _survey = MutableStateFlow<SurveyUiModel?>(null)
    val survey: StateFlow<SurveyUiModel?> = _survey

    private val questionSubmissions = mutableListOf<QuestionSubmission>()

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
            val surveySubmission = SurveySubmission(
                id = survey.id,
                questions = questionSubmissions
            )

            submitSurveyUseCase(surveySubmission)
                .injectLoading()
                .catch { e -> _error.emit(e) }
                .onEach {
                    _navigator.emit(AppDestination.Completion)
                }
                .launchIn(viewModelScope)
        }
    }

    fun saveAnswerForQuestion(questionSubmission: QuestionSubmission) {
        val question = questionSubmissions.find { it.id == questionSubmission.id }

        if (question == null) {
            questionSubmissions.add(questionSubmission)
        } else {
            question.answers.clear();
            question.answers.addAll(questionSubmission.answers);
        }
    }
}

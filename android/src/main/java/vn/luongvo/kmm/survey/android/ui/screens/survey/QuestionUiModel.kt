package vn.luongvo.kmm.survey.android.ui.screens.survey

import vn.luongvo.kmm.survey.domain.model.Question

data class QuestionUiModel(
    val id: String,
    val text: String,
    val displayType: DisplayType,
    val coverImageUrl: String,
    val answers: List<AnswerUiModel>
)

enum class DisplayType {
    STAR,
    HEART,
    SMILEY,
    CHOICE,
    NPS,
    TEXTAREA,
    TEXTFIELD,
    DROPDOWN,
    MONEY,
    SLIDER,
    INTRO,
    OUTRO,
    UNKNOWN;

    companion object {
        fun from(value: String?): DisplayType =
            values().find { it.name.equals(value, true) } ?: UNKNOWN
    }
}

fun Question.toUiModel() = QuestionUiModel(
    id = id,
    text = text.orEmpty(),
    displayType = DisplayType.from(displayType),
    coverImageUrl = coverImageUrl.orEmpty() + "l",
    answers = answers?.map { it.toUiModel() }.orEmpty()
)

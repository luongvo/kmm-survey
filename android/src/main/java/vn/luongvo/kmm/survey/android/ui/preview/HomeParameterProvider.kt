package vn.luongvo.kmm.survey.android.ui.preview

import androidx.compose.material.DrawerValue
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.home.SurveyUiModel
import vn.luongvo.kmm.survey.android.ui.screens.home.UserUiModel

class HomeParameterProvider : PreviewParameterProvider<HomeParameterProvider.Params> {

    private val userUiModel = UserUiModel(
        email = "luong@nimblehq.co",
        name = "Luong",
        avatarUrl = "https://secure.gravatar.com/avatar/8fae17b9d0c4cca18a9661bcdf650f23"
    )

    private val surveyUiModels = listOf(
        SurveyUiModel(
            id = "1",
            title = "Scarlett Bangkok",
            description = "We'd love to hear from you!",
            coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_"
        ),
        SurveyUiModel(
            id = "2",
            title = "ibis Bangkok Riverside",
            description = "We'd love to hear from you!",
            coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/287db81c5e4242412cc0_"
        )
    )

    override val values = sequenceOf(
        Params(
            isLoading = false,
        ),
        Params(
            isLoading = true,
        ),
        Params(
            isLoading = false,
            drawerState = DrawerValue.Open,
        )
    )

    inner class Params(
        val isLoading: Boolean,
        val drawerState: DrawerValue = DrawerValue.Closed,
        val appVersion: String = "v1.0.0",
        val currentDate: String = "Monday, JUNE 15",
        val user: UserUiModel = userUiModel,
        val surveys: List<SurveyUiModel> = surveyUiModels
    )
}

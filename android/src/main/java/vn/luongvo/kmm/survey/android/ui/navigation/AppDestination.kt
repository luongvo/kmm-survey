package vn.luongvo.kmm.survey.android.ui.navigation

import androidx.navigation.*

const val SurveyIdArg = "surveyId"

sealed class AppDestination(val route: String = "") {

    open val arguments: List<NamedNavArgument> = emptyList()

    open var destination: String = route

    //====================================================//

    object Up : AppDestination()

    object Root : AppDestination("root")

    object Login : AppDestination("login")

    object Home : AppDestination("home")

    object Survey : AppDestination("survey/{$SurveyIdArg}") {

        override val arguments = listOf(
            navArgument(SurveyIdArg) { type = NavType.StringType }
        )

        fun buildDestination(surveyId: String) = apply {
            destination = "survey/$surveyId"
        }
    }

    object Completion : AppDestination("completion")
}

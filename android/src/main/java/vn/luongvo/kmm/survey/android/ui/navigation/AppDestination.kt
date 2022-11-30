package vn.luongvo.kmm.survey.android.ui.navigation

import androidx.navigation.NamedNavArgument

sealed class AppDestination(val route: String = "") {

    open val arguments: List<NamedNavArgument> = emptyList()

    open var destination: String = route

    //====================================================//

    object Up : AppDestination()

    object Home : AppDestination("home")
}

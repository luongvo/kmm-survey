package vn.luongvo.kmm.survey.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import vn.luongvo.kmm.survey.android.ui.screens.home.HomeScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestination.Home.destination
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppDestination.Home) {
            HomeScreen(
                // TODO
                // navigator = { destination -> navController.navigate(destination) }
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    destination: AppDestination,
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.route,
        arguments = destination.arguments,
        deepLinks = deepLinks,
        content = content
    )
}

private fun NavHostController.navigate(destination: AppDestination) {
    when (destination) {
        is AppDestination.Up -> popBackStack()
        else -> navigate(route = destination.destination)
    }
}

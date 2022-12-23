package vn.luongvo.kmm.survey.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import vn.luongvo.kmm.survey.android.ui.screens.home.HomeScreen
import vn.luongvo.kmm.survey.android.ui.screens.login.LoginScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestination.Login.destination
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppDestination.Login) {
            LoginScreen(
                 navigator = { destination -> navController.navigate(destination) }
            )
        }
        composable(AppDestination.Home) {
            HomeScreen(
                // TODO handle navigation later
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

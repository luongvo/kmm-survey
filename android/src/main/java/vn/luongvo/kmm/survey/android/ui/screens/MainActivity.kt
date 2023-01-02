package vn.luongvo.kmm.survey.android.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.DrawerValue
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.*
import androidx.core.view.WindowCompat
import kotlinx.coroutines.launch
import timber.log.Timber
import vn.luongvo.kmm.survey.android.ui.navigation.AppNavigation
import vn.luongvo.kmm.survey.android.ui.screens.home.UserUiModel
import vn.luongvo.kmm.survey.android.ui.screens.home.views.Drawer
import vn.luongvo.kmm.survey.android.ui.screens.home.views.RtlModalDrawer
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposeTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer = {
        scope.launch {
            drawerState.open()
        }
    }
    var user by remember { mutableStateOf<UserUiModel?>(null) }

    RtlModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Drawer(
                user = user,
                onLogoutClick = {
                    // TODO https://github.com/luongvo/kmm-survey/issues/19
                    Timber.d("onLogoutClick")
                }
            )
        }
    ) {
        AppNavigation(
            onDrawerUiStateChange = { user = it },
            onOpenDrawer = { openDrawer() }
        )
    }
}

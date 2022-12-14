package vn.luongvo.kmm.survey.android.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import vn.luongvo.kmm.survey.android.ui.navigation.AppNavigation
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposeTheme {
                AppNavigation()
            }
        }
    }
}

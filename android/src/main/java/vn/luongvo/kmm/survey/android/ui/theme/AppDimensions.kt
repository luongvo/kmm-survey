package vn.luongvo.kmm.survey.android.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class AppDimensions {
    val paddingSmall: Dp = 16.dp
    val paddingMedium: Dp = 20.dp
    val paddingLarge: Dp = 24.dp

    val inputHeight: Dp = 56.dp
    val buttonHeight: Dp = 56.dp
    val avatarSize: Dp = 36.dp
}

internal val LocalAppDimensions = staticCompositionLocalOf { AppDimensions() }

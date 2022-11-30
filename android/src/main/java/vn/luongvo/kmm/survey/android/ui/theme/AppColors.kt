package vn.luongvo.kmm.survey.android.ui.theme

import androidx.compose.material.*
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Extend final [Colors] class to provide more custom app colors.
 */
data class AppColors(
    val themeColors: Colors,

    // Custom colors here
)

internal val LightColorPalette = AppColors(
    themeColors = lightColors(
        primary = Color(0xFF6200EE),
        primaryVariant = Color(0xFF3700B3),
        secondary = Color(0xFF03DAC5)
    )
)

internal val DarkColorPalette = AppColors(
    themeColors = darkColors(
        primary = Color(0xFFBB86FC),
        primaryVariant = Color(0xFF3700B3),
        secondary = Color(0xFF03DAC5)
    )
)

internal val LocalColors = staticCompositionLocalOf { LightColorPalette }

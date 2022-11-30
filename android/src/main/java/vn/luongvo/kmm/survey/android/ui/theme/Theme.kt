@file:Suppress("MatchingDeclarationName")

package vn.luongvo.kmm.survey.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val dimensions = LocalAppDimensions.current
    val styles = LocalAppStyles.current

    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalAppDimensions provides dimensions,
        LocalAppStyles provides styles
    ) {
        MaterialTheme(
            colors = colors.themeColors,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

/**
 * Alternate to [MaterialTheme] allowing us to add our own theme systems
 * or to extend [MaterialTheme]'s types e.g. return our own [Colors] extension.
 */
object AppTheme {

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes

    val dimensions: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalAppDimensions.current

    val styles: AppStyles
        @Composable
        @ReadOnlyComposable
        get() = LocalAppStyles.current
}

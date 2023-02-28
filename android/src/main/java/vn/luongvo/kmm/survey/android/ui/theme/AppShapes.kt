package vn.luongvo.kmm.survey.android.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

private val Shapes = Shapes(
    medium = RoundedCornerShape(10.dp)
)

internal val LocalAppShapes = staticCompositionLocalOf { Shapes }

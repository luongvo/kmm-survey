package vn.luongvo.kmm.survey.android.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.sp
import vn.luongvo.kmm.survey.android.R

private val Neuzeit = FontFamily(
    Font(R.font.neuzeit_book, FontWeight.Normal),
    Font(R.font.neuzeit_heavy, FontWeight.Bold)
)

private val Typography = Typography(
    defaultFontFamily = Neuzeit,
    body1 = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal
    ),
    body2 = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal
    ),
    subtitle1 = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal
    ),
    h4 = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold
    ),
    h5 = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    ),
    h6 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),
    button = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold
    )
)

internal val LocalAppTypography = staticCompositionLocalOf { Typography }

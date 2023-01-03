package vn.luongvo.kmm.survey.android.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions

private const val Rotate180 = 180f

@Composable
fun BackButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .wrapContentSize()
            .rotate(Rotate180), // switch Right Arrow to Back button
        contentPadding = PaddingValues(dimensions.paddingMedium),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Transparent
        ),
        elevation = null
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White),
            contentScale = ContentScale.FillWidth
        )
    }
}

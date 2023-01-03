package vn.luongvo.kmm.survey.android.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color.Companion.White
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
    Image(
        painter = painterResource(id = R.drawable.ic_arrow_right),
        contentDescription = null,
        colorFilter = ColorFilter.tint(White),
        contentScale = ContentScale.FillWidth,
        modifier = modifier
            .wrapContentSize()
            .rotate(Rotate180) // switch Right Arrow to Back button
            .clickable { onClick() }
            .padding(all = dimensions.paddingMedium)
    )
}

package vn.luongvo.kmm.survey.android.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import vn.luongvo.kmm.survey.android.ui.theme.Black20

@Composable
fun DimmedImageBackground(@DrawableRes imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .matchParentSize()
                    .blur(radius = 25.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Black20,
                                Black
                            )
                        )
                    )
            )
        }
    }
}

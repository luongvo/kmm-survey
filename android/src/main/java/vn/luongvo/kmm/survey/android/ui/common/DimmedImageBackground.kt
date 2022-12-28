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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import vn.luongvo.kmm.survey.android.ui.theme.Black20

@Composable
fun DimmedImageBackground(
    @DrawableRes imageRes: Int? = null,
    imageUrl: String? = null,
    blurRadius: Dp = 0.dp,    // Default with no blur
    gradientAlpha: Float = 1f // Default with vertical gradient
) {
    Box(modifier = Modifier.fillMaxSize()) {
        imageRes?.let {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .blur(radius = blurRadius)
            )
        }
        imageUrl?.let {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .blur(radius = blurRadius)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Black.copy(alpha = gradientAlpha * Black20.alpha),
                            Black.copy(alpha = gradientAlpha)
                        )
                    )
                )
        )
    }
}

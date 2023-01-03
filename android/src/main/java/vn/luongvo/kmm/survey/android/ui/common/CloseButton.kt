package vn.luongvo.kmm.survey.android.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.luongvo.kmm.survey.android.R

@Composable
fun CloseButton(
    onClick: () -> Unit,
    modifier: Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_close),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = modifier
            .size(28.dp)
            .clip(CircleShape)
            .clickable { onClick() }
    )
}

@Preview
@Composable
fun CloseButtonPreview() {
    CloseButton(
        onClick = {},
        modifier = Modifier
    )
}

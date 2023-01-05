package vn.luongvo.kmm.survey.android.ui.common

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions

@Composable
fun NextCircleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_arrow_right),
        contentDescription = null,
        contentScale = ContentScale.Inside,
        modifier = modifier
            .size(dimensions.buttonHeight)
            .clip(CircleShape)
            .background(White)
            .clickable { onClick() }
    )
}

@Preview
@Composable
fun NextCircleButtonPreview() {
    NextCircleButton(
        onClick = {}
    )
}

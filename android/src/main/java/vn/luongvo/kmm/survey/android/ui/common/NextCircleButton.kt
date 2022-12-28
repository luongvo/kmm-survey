package vn.luongvo.kmm.survey.android.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions

@Composable
fun NextCircleButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(dimensions.buttonHeight),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = White
        ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview
@Composable
fun NextCircleButtonPreview() {
    NextCircleButton(
        onClick = {}
    )
}

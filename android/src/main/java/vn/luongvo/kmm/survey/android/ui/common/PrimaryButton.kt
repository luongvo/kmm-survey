package vn.luongvo.kmm.survey.android.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.shapes
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography
import vn.luongvo.kmm.survey.android.ui.theme.BlackRussian

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        shape = shapes.medium,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensions.buttonHeight)
    ) {
        Text(
            text = text,
            color = BlackRussian,
            style = typography.button
        )
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        text = "Button Text",
        onClick = {}
    )
}

package vn.luongvo.kmm.survey.android.ui.screens.completion

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

@Composable
fun CompletionScreen() {
    //  TODO https://github.com/luongvo/kmm-survey/issues/35
    Box {
        Text(
            text = "Completion Page",
            color = White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CompletionScreenPreview() {
    ComposeTheme {
        CompletionScreen()
    }
}

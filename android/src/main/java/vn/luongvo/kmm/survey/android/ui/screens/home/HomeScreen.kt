package vn.luongvo.kmm.survey.android.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import vn.luongvo.kmm.survey.Greeting
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Text(text = Greeting().greeting())
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    ComposeTheme {
        HomeScreen()
    }
}

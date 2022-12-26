package vn.luongvo.kmm.survey.android.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.getViewModel
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Home",
            color = White,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
    ComposeTheme {
        HomeScreen()
    }
}

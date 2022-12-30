package vn.luongvo.kmm.survey.android.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.getViewModel
import vn.luongvo.kmm.survey.android.ui.screens.home.views.HomeHeader
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel()
) {
    HomeScreenContent()
}

@Composable
private fun HomeScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            // TODO demo background, integrate in https://github.com/luongvo/kmm-survey/issues/16
            .background(Color(0xFF8E9398))
    ) {
        Column(
            modifier = Modifier.statusBarsPadding()
        ) {
            HomeHeader(
                // TODO Integrate in https://github.com/luongvo/kmm-survey/issues/13
                dateTime = "Monday, JUNE 15",
                avatarUrl = "https://secure.gravatar.com/avatar/8fae17b9d0c4cca18a9661bcdf650f23"
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
    ComposeTheme {
        HomeScreenContent()
    }
}

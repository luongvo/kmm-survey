package vn.luongvo.kmm.survey.android.ui.screens.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.getViewModel
import vn.luongvo.kmm.survey.Greeting
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

@Composable
fun LoginScreen(viewModel: LoginViewModel = getViewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Text(text = Greeting().greeting())
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    ComposeTheme {
        LoginScreen()
    }
}

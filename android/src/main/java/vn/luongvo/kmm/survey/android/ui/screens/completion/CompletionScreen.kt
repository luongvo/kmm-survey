package vn.luongvo.kmm.survey.android.ui.screens.completion

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.delay
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

private const val ANIMATION_PROGRESS_COMPLETED = 1.0f
private const val ANIMATION_DELAY_FOR_NAVIGATION = 1000L

@Composable
fun CompletionScreen(
    navigator: (destination: AppDestination) -> Unit
) {
    CompletionScreenContent {
        navigator(AppDestination.Up)
    }
}

@Composable
private fun CompletionScreenContent(
    onAnimationCompleted: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.completion_success))
    val progress by animateLottieCompositionAsState(composition)

    LaunchedEffect(progress) {
        if (progress == ANIMATION_PROGRESS_COMPLETED) {
            delay(ANIMATION_DELAY_FOR_NAVIGATION)
            onAnimationCompleted()
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.dimensions.paddingLarge)
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.completion_thanks_message),
            color = White,
            style = typography.h5,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CompletionScreenPreview() {
    ComposeTheme {
        CompletionScreenContent {}
    }
}

package vn.luongvo.kmm.survey.android.ui.screens.login

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.common.*
import vn.luongvo.kmm.survey.android.ui.navigation.AppDestination
import vn.luongvo.kmm.survey.android.ui.preview.LoadingParameterProvider
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme
import vn.luongvo.kmm.survey.android.ui.theme.White50
import vn.luongvo.kmm.survey.android.util.userReadableMessage

const val LoginEmailField = "LoginEmailField"
const val LoginPasswordField = "LoginPasswordField"
const val LoginButton = "LoginButton"

const val FirstPhaseDurationInMilliseconds = 800
const val StayPhaseDurationInMilliseconds = 500
const val LastPhaseDurationInMilliseconds = 700
private const val BlurRadius = 25f
private val LogoOffset = Offset(0f, -229f)

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = getViewModel(),
    navigator: (destination: AppDestination) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val isLoggedIn by viewModel.isLoggedIn.collectAsStateWithLifecycle()

    val context = LocalContext.current
    error?.let {
        AlertDialog(
            message = it.userReadableMessage(context),
            onDismissRequest = { viewModel.clearError() }
        )
    }

    LaunchedEffect(viewModel.navigator) {
        viewModel.navigator.collect { destination -> navigator(destination) }
    }

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    if (isLoggedIn == false) {
        LoginScreenContent(
            email = email,
            password = password,
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            onLogInClick = { viewModel.logIn(email, password) },
            isLoading = isLoading
        )
    }
}

@Composable
private fun LoginScreenContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogInClick: () -> Unit,
    isLoading: Boolean,
    initialLogoVisible: Boolean = false,
    initialLogoOffset: Offset = Offset(0f, 0f),
    initialLogoScale: Float = 1.2f,
    initialBlurState: Float = 0f,
    initialAlphaState: Float = 0f
) {
    var logoVisible by remember { mutableStateOf(initialLogoVisible) }
    var logoOffsetState by remember { mutableStateOf(initialLogoOffset) }
    var logoScaleState by remember { mutableStateOf(initialLogoScale) }
    var alphaState by remember { mutableStateOf(initialAlphaState) }
    var blurState by remember { mutableStateOf(initialBlurState) }

    val floatTweenSpec = tween<Float>(
        durationMillis = LastPhaseDurationInMilliseconds,
        delayMillis = StayPhaseDurationInMilliseconds
    )
    val animateBlurState by animateFloatAsState(
        targetValue = blurState,
        floatTweenSpec
    )
    val animateAlphaState by animateFloatAsState(
        targetValue = alphaState,
        floatTweenSpec
    )
    val logoScaleAnimate by animateFloatAsState(
        targetValue = logoScaleState,
        floatTweenSpec
    )
    val logoOffsetAnimate by animateOffsetAsState(
        targetValue = logoOffsetState,
        tween(
            durationMillis = LastPhaseDurationInMilliseconds,
            delayMillis = StayPhaseDurationInMilliseconds
        )
    )
    LaunchedEffect(Unit) {
        delay(FirstPhaseDurationInMilliseconds.toLong())
        logoVisible = true
        blurState = BlurRadius
        alphaState = 1f
        logoOffsetState = LogoOffset
        logoScaleState = 1f
    }

    Box {
        DimmedImageBackground(
            imageRes = R.drawable.bg_login,
            blurRadius = animateBlurState.dp,
            gradientAlpha = animateAlphaState
        )
        AnimatedVisibility(
            visible = logoVisible,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_nimble_logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(all = dimensions.paddingLarge)
                    .offset(logoOffsetAnimate.x.dp, logoOffsetAnimate.y.dp)
                    .scale(logoScaleAnimate)
            )
        }
        LoginForm(
            modifier = Modifier.alpha(animateAlphaState),
            email = email,
            password = password,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onLogInClick = onLogInClick
        )

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }
    }
}

@Composable
private fun LoginForm(
    modifier: Modifier,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogInClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensions.paddingMedium),
        modifier = modifier
            .fillMaxSize()
            .wrapContentHeight()
            .padding(horizontal = dimensions.paddingLarge)
    ) {
        PrimaryTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholder = stringResource(id = R.string.login_email),
            keyboardType = KeyboardType.Email,
            contentDescription = LoginEmailField
        )
        Box {
            PrimaryTextField(
                value = password,
                onValueChange = onPasswordChange,
                placeholder = stringResource(id = R.string.login_password),
                visualTransformation = PasswordVisualTransformation(),
                imeAction = ImeAction.Done,
                contentDescription = LoginPasswordField
            )
            Text(
                text = stringResource(id = R.string.login_forgot),
                color = White50,
                style = typography.body2,
                modifier = Modifier
                    .wrapContentHeight()
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp)
            )
        }
        PrimaryButton(
            text = stringResource(id = R.string.login_button),
            onClick = onLogInClick,
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = LoginButton },
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview(
    @PreviewParameter(LoadingParameterProvider::class) isLoading: Boolean
) {
    ComposeTheme {
        LoginScreenContent(
            email = "",
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onLogInClick = {},
            isLoading = isLoading,
            initialLogoVisible = true,
            initialLogoOffset = LogoOffset,
            initialLogoScale = 1f,
            initialBlurState = BlurRadius,
            initialAlphaState = 1f
        )
    }
}

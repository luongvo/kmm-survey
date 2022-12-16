package vn.luongvo.kmm.survey.android.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.common.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

@Composable
fun LoginScreen(viewModel: LoginViewModel = getViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LoginScreenContent(
        email = email,
        password = password,
        onEmailChange = {
            email = it
            // TODO https://github.com/luongvo/kmm-survey/issues/8
        },
        onPasswordChange = {
            password = it
            // TODO https://github.com/luongvo/kmm-survey/issues/8
        },
    )
}

@Composable
private fun LoginScreenContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
) {
    Box {
        Box(modifier = Modifier.fillMaxSize()) {
            DimmedImageBackground(imageRes = R.drawable.bg_login)
        }
        Image(
            painter = painterResource(id = R.drawable.ic_nimble_logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentHeight()
                .offset(0f.dp, (-229f).dp)
                .scale(1.0f / 1.2f)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(dimensions.paddingMedium),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .padding(horizontal = dimensions.paddingLarge)
        ) {
            CustomTextField(
                value = email,
                onValueChange = onEmailChange,
                placeholder = stringResource(id = R.string.login_email),
                keyboardType = KeyboardType.Email,
            )
            CustomTextField(
                value = password,
                onValueChange = onPasswordChange,
                placeholder = stringResource(id = R.string.login_password),
                visualTransformation = PasswordVisualTransformation(),
                imeAction = ImeAction.Done,
            )
            CustomButton(
                text = stringResource(id = R.string.login_button),
                onClick = {
                    // TODO https://github.com/luongvo/kmm-survey/issues/8
                },
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    ComposeTheme {
        LoginScreenContent(
            email = "",
            password = "",
            onEmailChange = {},
            onPasswordChange = {}
        )
    }
}

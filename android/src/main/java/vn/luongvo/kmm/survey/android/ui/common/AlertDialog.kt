package vn.luongvo.kmm.survey.android.ui.common

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.theme.Nero90

@Composable
fun AlertDialog(
    message: String,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit = onDismissRequest
) {
    AlertDialog(
        text = { Text(text = message) },
        backgroundColor = White,
        confirmButton = {
            Button(
                onClick = { onConfirmButtonClick() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Nero90
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.generic_ok),
                    color = White
                )
            }
        },
        onDismissRequest = { onDismissRequest() }
    )
}

@Composable
@Preview(showBackground = true)
fun AlertDialogPreview() {
    AlertDialog(
        message = "Message",
        onDismissRequest = {}
    )
}

package vn.luongvo.kmm.survey.android.ui.common

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.theme.Nero90

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit = onDismissRequest,
    onDismissButtonClick: () -> Unit = onDismissRequest
) {
    AlertDialog(
        title = { Text(text = title) },
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
                    text = stringResource(id = R.string.generic_yes),
                    color = White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismissButtonClick() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Nero90
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.generic_no),
                    color = White
                )
            }
        },
        onDismissRequest = { onDismissRequest() }
    )
}

@Preview(showBackground = true)
@Composable
fun ConfirmationDialogPreview() {
    ConfirmationDialog(
        title = "Title",
        message = "Message",
        onDismissRequest = {}
    )
}

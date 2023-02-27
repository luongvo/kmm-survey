package vn.luongvo.kmm.survey.android.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.shapes
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography
import vn.luongvo.kmm.survey.android.ui.theme.White18
import vn.luongvo.kmm.survey.android.ui.theme.White30

@Composable
fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    contentDescription: String = "",
    isHighlightBackgroundIfNotEmpty: Boolean = false,
    singleLine: Boolean = true
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = typography.body1
            )
        },
        singleLine = singleLine,
        colors = TextFieldDefaults.textFieldColors(
            textColor = White,
            backgroundColor = if (isHighlightBackgroundIfNotEmpty && value.isNotEmpty()) White30 else White18,
            cursorColor = White,
            unfocusedIndicatorColor = Transparent,
            focusedIndicatorColor = Transparent,
            disabledIndicatorColor = Transparent,
            placeholderColor = White30
        ),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = dimensions.inputHeight)
            .clip(shapes.medium)
            .semantics { this.contentDescription = contentDescription }
    )
}

@Preview
@Composable
fun PrimaryTextFieldPreview() {
    PrimaryTextField(
        value = "",
        placeholder = "Email",
        onValueChange = {}
    )
}

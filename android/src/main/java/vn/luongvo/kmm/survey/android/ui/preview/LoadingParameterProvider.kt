package vn.luongvo.kmm.survey.android.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class LoadingParameterProvider : PreviewParameterProvider<Boolean> {

    override val values = sequenceOf(
        false,
        true
    )
}
